package com.babyshop.babyshop.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.babyshop.babyshop.models.CustomOidcUser;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.repositories.RoleRepository;
import com.babyshop.babyshop.repositories.UserRepository;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.RandomKey;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ImageService imageService;
	
	@Autowired
	HttpSession session;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		RandomKey randomKey = new RandomKey();
		String redirectUrl = null;
		if (authentication.getPrincipal() instanceof OidcUser) {
			CustomOidcUser customOidcUser = (CustomOidcUser) authentication.getPrincipal();
			String email = customOidcUser.getEmail();

			if (userRepo.findByEmail(email) == null) {
				User user = new User();
				user.setEmail(email);
				user.setFullName(customOidcUser.getName());
				user.setPassword(randomKey.getString(32));
				UrlResource urlResource = new UrlResource(customOidcUser.getPicture());
				String avatar = imageService.storeAvatar(urlResource);
				user.setImage(new Image(avatar));
				userService.saveUser(user);
				System.out.print("user: " + user.getEmail());
			}
			User user = userService.findByEmail(email);
			System.out.print("user: " + user.getEmail());
			session.setAttribute("user", user);
		}
		redirectUrl = "/viewprofile";

		// chuyển tiếp tới địa chỉ
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
//	"sub": ID của người dùng trên Google
//	"name": Tên đầy đủ của người dùng
//	"given_name": Tên của người dùng
//	"family_name": Họ của người dùng
//	"email": Địa chỉ email của người dùng
//	"picture": Ảnh đại diện của người dùng

}
