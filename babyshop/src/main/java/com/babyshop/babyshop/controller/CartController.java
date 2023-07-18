package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.babyshop.babyshop.models.Cart;
import com.babyshop.babyshop.models.CartItem;
import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.UserService;

import jakarta.servlet.http.HttpSession;
@Controller
public class CartController {
	@Autowired
	LoadDataController loadDataController;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/user/cart")
	public String cart(ModelMap modelMap) {
		loadDataController.loadData(modelMap); 
		List<CartItem> cartItems  = new ArrayList<>();
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// user.getUserId()
		Customer customer = user.getCustomer();
		if(customer!=null) {
			Cart cart = customer.getCart();
			if(cart!=null) {
				cartItems = cart.getCartItem();
			} 
		}
		modelMap.addAttribute("cartItems", cartItems);
		return "cart";
	}
	 
	
}
