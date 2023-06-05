package com.babyshop.babyshop.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.babyshop.babyshop.service.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomOidcUser implements OidcUser {
	private UserService userService;
	private Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
	
    private final OidcUser oidcUser;

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }
    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return oidcUser.getName();
    }
    
    public String getEmail() {
    	return oidcUser.getAttribute("email");
    }
    
    public String getAvatar() {
    	return oidcUser.getAttribute("picture");
    }
    
    public String getFullName() {
    	return oidcUser.getAttribute("name");
    }
}