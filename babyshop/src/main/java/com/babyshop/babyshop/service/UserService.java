package com.babyshop.babyshop.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        User user = findByEmail(emailOrPhone);
        if(user == null) {
        	user = findByPhone(emailOrPhone);
        }
        if (user != null) {
            return user; 
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
       
    }
    
    public User findByEmail(String email) {
    	User user = userRepository.findByEmail(email);
    	return user;
    }
    
    public User findByPhone(String phone) {
    	User user = userRepository.findByPhoneNumber(phone);
    	return user;
    }
    @Transactional
    public void saveUser(User user) {
    	//Kiểm tra xem role đã tồn tại hay chưa
    	Role role = roleRepository.findByName(Permit.CUSTOMER);
    	if(role == null) {
    		role = new Role(Permit.CUSTOMER);
    	} 
    	//Build user và lưu vào database
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setRoles(Arrays.asList(role));
    	//merge lại vào trong JPA, lỗi này chịu :))
    	entityManager.merge(role);
    	userRepository.save(user); 
    } 
    
    public void saveUserEncoder(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    }
    
    public boolean userIsExists(String key) {
    	User user1 = findByEmail(key);
    	User user2 = findByPhone(key);
    	return user1 != null || user2 != null ;
    }
    
    public User getUserById(int userId) {
    	Optional<User> user = userRepository.findById(userId);
    	if(user.isPresent()) return user.get();
    	return null;
    }
    public void save(User user) {
    	userRepository.save(user);
    }
    
    public User getUserByIdExists(int userId) {
    	User user = userRepository.findByUserExsist(userId);
    	return user;
    }
    
    public List<User> findAllUsers(){
    	return userRepository.findAllUsers();
    }
}
