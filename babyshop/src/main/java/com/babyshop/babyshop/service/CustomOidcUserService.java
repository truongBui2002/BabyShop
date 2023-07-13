package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.CustomOidcUser;
import com.babyshop.babyshop.models.User;

@Service
public class CustomOidcUserService extends OidcUserService {
	@Autowired
	UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUser oidcUser = super.loadUser(userRequest);
        CustomOidcUser customOidcUser =  new CustomOidcUser(oidcUser);
        User user = userService.findByEmail(customOidcUser.getEmail());
        if(user!=null) {
        	customOidcUser.setAuthorities(user.getAuthorities());
        }
        return customOidcUser;
    }
    
}