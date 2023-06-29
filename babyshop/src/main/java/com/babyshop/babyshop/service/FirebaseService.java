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

	public void getUserByToken(String token) {
		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
		// Xác thực mã thông báo truy cập
		FirebaseToken decodedToken = null;
		try {
			decodedToken = firebaseAuth.verifyIdToken(token);
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		// Lấy thông tin người dùng từ decoded token
		String uid = "";
		if(decodedToken!=null) {
			uid = decodedToken.getUid();
			String email = decodedToken.getEmail();
			String displayName = decodedToken.getName();
			String photoUrl = decodedToken.getPicture(); // Lấy ảnh đại diện
			System.out.println(uid);
			System.out.println(email);
			System.out.println(displayName);
			System.out.println(photoUrl);
		}else {
			System.out.print("Loi het roi");
		}
//		deleteToken(token, uid);
	}
	
	public void deleteToken(String token, String uid){
		
		
		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
		
		try {
			firebaseAuth.verifyIdToken(token);
			//Xóa bỏ các token của người dùng 
			firebaseAuth.revokeRefreshTokens(uid);
			//Kiểm tra Token còn hoạt động
		    System.out.println("Token is valid.");
		    System.out.println(firebaseAuth.verifyIdToken(token).getName());;
		} catch (FirebaseAuthException e) {
		    System.out.println("Invalid or revoked token.");
		}
	}
}
