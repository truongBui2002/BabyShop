package com.babyshop.babyshop.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.babyshop.babyshop.models.Role;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.repositories.RoleRepository;
import com.babyshop.babyshop.repositories.UserRepository;
import com.babyshop.babyshop.util.Permit;


@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        User user = findByEmail(emailOrPhone);
        if(user == null) {
        	
        }
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
       
    }
    
    public User findByEmail(String email) {
    	User user = userRepository.findByEmail(email);;
    	return user;
    }
    
    public User findByPhone(String phone) {
    	User user = userRepository.findByPhoneNumber(phone);
    	return user;
    }
    
    public void saveUser(User user) {
    	//Kiểm tra xem role đã tồn tại hay chưa
    	Role role = roleRepository.findByName(Permit.CUSTOMER);
    	if(role == null) {
    		role = new Role(Permit.CUSTOMER);
    	}
    	//Build user và lưu vào database
    	userRepository.save(User
    			.builder()
    			.email(user.getEmail())
    			.phoneNumber(user.getPhoneNumber())
    			.password(passwordEncoder.encode(user.getPassword()))
    			.roles(Arrays.asList(role))
    			.build());
    }
}
