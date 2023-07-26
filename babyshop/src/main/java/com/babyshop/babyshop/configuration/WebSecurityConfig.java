package com.babyshop.babyshop.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.babyshop.babyshop.models.CustomOidcUser;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.CustomOidcUserService;
import com.babyshop.babyshop.util.Permit;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	@Autowired
	CustomOidcUserService customOidcUserService;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()//Tắt tính năng bảo mật mặc đinh
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").hasRole("CUSTOMER")
                        .requestMatchers("/manager/dashboard").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/manager/user-profile").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers("/manager/staff/**").hasRole("STAFF")
                        .requestMatchers("/manager/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                		//Địa chỉ request trong controller("/login")
                        .loginPage("/login")
                        //Địa chỉ xác thực form (POST)
                        .loginProcessingUrl("/login")// successForwardUrl() địa chỉ xác thực bằng đường dẫn (GET)
                        //Nếu thành công thì chuyển tới
                        .successHandler(successHandler)
                        //Tên của biến trong form dùng để gửi khi xác thực (name của input)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        //Nếu login thất bại thì chuyển tới
                        .failureUrl("/login?error")
                        .permitAll()
                ).oauth2Login(auth -> auth
                		//địa chỉ trang login with google
                        .loginPage("/login")
                        //xác thực và lấy thông tin trả về từ google
                        .userInfoEndpoint(userInfoEndpoint ->
                         userInfoEndpoint.oidcUserService(customOidcUserService))
                        //xác thực thành công thì làm gì tiếp  theo
                        .successHandler(successHandler)
                        //xác thực thất bại thì chuyển tới 
                        .failureUrl("/")
                )
                //Cho phép truy cập vào /logout để đăng xuất mà không yêu cầu xác thực
                .logout((logout) -> logout
                		.logoutSuccessUrl("/login")
                		.permitAll())
                //Nếu truy cập đường dẫn không có đủ quyền thì truy cập tới 
                .exceptionHandling().accessDeniedPage("/error/e1");
        return http.build();
    }
    
    //Cấu hình các tài nguyên mà không cần qua quá trình xác thực
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }

    
}