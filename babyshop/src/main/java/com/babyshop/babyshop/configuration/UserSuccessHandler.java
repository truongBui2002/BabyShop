package com.babyshop.babyshop.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.babyshop.babyshop.models.CustomOidcUser;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Role;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.repositories.RoleRepository;
import com.babyshop.babyshop.repositories.UserRepository;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.Permit;
import com.babyshop.babyshop.util.RandomKey;
import com.babyshop.babyshop.util.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserService userService;

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
		String redirectUrl = "/user/viewprofile";
		String urlProductPre = (String) session.getAttribute("urlProductPre");
		User user = null;

		if (urlProductPre != null) {
			redirectUrl = urlProductPre;
		}
		if (authentication.getPrincipal() instanceof OidcUser) {
			CustomOidcUser customOidcUser = (CustomOidcUser) authentication.getPrincipal();
			String email = customOidcUser.getEmail();

			if (userService.findByEmail(email) == null) {
				User newUser = new User();
				newUser.setEmail(email);
				newUser.setFullName(customOidcUser.getFullName());
				newUser.setPassword(randomKey.getString(32));
				UrlResource urlResource = new UrlResource(customOidcUser.getPicture());
				String avatar = imageService.saveAvatarUrl(urlResource);
				newUser.setImage(new Image(avatar));
				newUser.setStatus(Status.NOT_PASSWORD);
				userService.saveUser(newUser);
			}
			user = userService.findByEmail(email);
		} else if (authentication.getPrincipal() instanceof UserDetails) {
			user = (User) authentication.getPrincipal();
		}
		// kiểm tra role truy cập
		boolean checkRole = false;
		if (user != null) {
			for (Role role : user.getRoles()) {
				if (role.getName().equals(Permit.ADMIN) || role.getName().equals(Permit.STAFF)) {
					checkRole = true;
				}
			}
			if (checkRole) {
				redirectUrl = "/manager/dashboard";
				session.setAttribute("manager", user);
			} else {
				session.setAttribute("user", user);
			}

		} else {
			redirectUrl = "/login";
		}

//		List<SimpleGrantedAuthority> list = (List<SimpleGrantedAuthority>)authentication.getAuthorities();
//		System.out.println("ROLE: " + list.get(0).getAuthority());
		// chuyển tiếp tới địa chỉ
		response.sendRedirect(redirectUrl);
		// new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
//	"sub": ID của người dùng trên Google
//	"name": Tên đầy đủ của người dùng
//	"given_name": Tên của người dùng
//	"family_name": Họ của người dùng
//	"email": Địa chỉ email của người dùng
//	"picture": Ảnh đại diện của người dùng

}
