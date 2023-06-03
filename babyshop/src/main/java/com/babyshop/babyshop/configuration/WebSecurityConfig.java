package com.babyshop.babyshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()//Tắt tính năng bảo mật mặc đinh
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                		//Địa chỉ request trong controller("/login")
                        .loginPage("/login")
                        //Địa chỉ xác thực form (POST)
                        .loginProcessingUrl("/login")// successForwardUrl() địa chỉ xác thực bằng đường dẫn (GET)
                        //Nếu thành công thì chuyển tới
                        .defaultSuccessUrl("/user/")
                        //Tên của biến trong form dùng để gửi khi xác thực (name của input)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        //Nếu login thất bại thì chuyển tới
                        .failureUrl("/login?error")
                        .permitAll()
                )
                //Cho phép truy cập vào /logout để đăng xuất mà không yêu cầu xác thực
                .logout((logout) -> logout
//                		.logoutSuccessUrl("/home")
                		.permitAll())
                //Nếu truy cập đường dẫn không có đủ quyền thì truy cập tới 
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }
    
    
    
    //Cấu hình các tài nguyên mà không cần qua quá trình xác thực
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }
    
//    Xóa mọi thông tin về xác thực của Spring Security khi tắt trình duyệt
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
