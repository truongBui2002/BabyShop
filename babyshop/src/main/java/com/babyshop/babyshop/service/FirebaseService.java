package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class FirebaseService {
	@Autowired
	FirebaseApp firebaseApp;
 
	public String getPhoneNumberByToken(String token) {
		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
		// Xác thực mã thông báo truy cập
		FirebaseToken decodedToken = null;
		try {
			decodedToken = firebaseAuth.verifyIdToken(token);
			if (decodedToken != null) {
				String phoneNumber = (String) decodedToken.getClaims().get("phone_number");//uid là số điện thoại vì dùng sđt để xác thực
				return phoneNumber;
			}
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
}
