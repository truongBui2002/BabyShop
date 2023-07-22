package com.babyshop.babyshop.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.models.Cart;
import com.babyshop.babyshop.models.CartItem;
import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.CartItemService;
import com.babyshop.babyshop.service.CartService;
import com.babyshop.babyshop.service.CookiesService;
import com.babyshop.babyshop.service.CustomerService;
import com.babyshop.babyshop.service.ProductService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.service.VariantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@RestController
public class CartRestController {
	@Autowired
	CookiesService cookiesService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VariantService variantService;
	
	@Autowired
	CartService caService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartItemService cartItemService;

	@PutMapping("/user/product/cart")
	public String addToCart(@RequestBody String vId) throws Exception {
		//System.out.println("DATA: " + vId);
		int variantId = Integer.parseInt(vId);
		
		User user = (User)session.getAttribute("user"); // để lại
		user = userService.getUserById(user.getUserId());// user.getUserId()
		Customer customer = user.getCustomer();
		if(user.getCustomer()==null) {
			customer = new Customer();
			customer.setUser(user);
			user.setCustomer(customer);
			userService.save(user);
			//cập nhật user khi user thay đổi
			user = userService.getUserById(user.getUserId());
			customer = user.getCustomer();
		} 
		Cart cart = customer.getCart(); 
		if(customer.getCart()==null) {
			cart = new Cart();
			cart.setCustomer(customer);
			customer.setCart(cart);
			customerService.save(customer); 
			//cập nhật user khi user thay đổi
			user = userService.getUserById(user.getUserId());
			customer = user.getCustomer();
			cart = customer.getCart();
		} 
		
		List<CartItem> cartItems = cart.getCartItem();
		Variant variant = variantService.getVariantById(variantId);
		
		boolean isVariantExists = false;
		boolean variantLimitedQuantiy = false;
		for (CartItem cartItem : cartItems) {
			Variant variantCartItem = cartItem.getVariant();
			//Kiểm tra xem variant trong List<CartItem> đã tồn tại hay chưa
			if(variantCartItem.getVariantId()== variantId) {
				isVariantExists = true;
				//kiểm tra xem số lượng của variant trong trong CartItem đã đạt tới giới hạn trong kho hay chưa
				if(cartItem.getQuantity()==variant.getQuantity()) {
					//System.out.println("cartItem is limited");
					variantLimitedQuantiy = true;
				}else {
					//System.out.println("cartItem: quantity + 1");
					//chưa đạt tới giới hạn của sản phẩm thì cộng thêm 1
					cartItem.setQuantity(cartItem.getQuantity()+1);
					cartItemService.save(cartItem);
				}
			} 
		}
		//variant không tồn tại trong List<CartItem> thì thêm mới CartItem
		if(!isVariantExists) {
			//System.out.println("Add variant to CartItem");
			//set up cartItem
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setVariant(variant);
			//mặc định set quantity khi thêm lần đầu là 1
			cartItem.setQuantity(1);
			cartItems.add(cartItem);
			cartService.save(cart);
			cart = cartService.getById(cart.getCartId());
		}	 
		//System.out.println("Loi o day");
		//user = userService.getUserById(user.getUserId());
		session.setAttribute("user", user);
		// Thông tin truyền đi gồm;
		// - variantLimitedQuantiy: sản phẩm được thêm đã đạt tới giới hạn trong kho hay chưa
		// - cập nhật số sản phẩm trong giỏ : cartItems.size()
		
		List<Object> data = new ArrayList<>();
		data.add(variantLimitedQuantiy);//đạt giới hạn thì in ra thông báo
		data.add(cartItems.size());//hiển thị số sản phẩm trong giỏ
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(data);
	}  
	 
	@PutMapping("/product/cart/authen")
	public boolean authen(@RequestBody String url) {
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			return true;
		}
		session.setAttribute("urlProductPre", url);
		return false;
	}
	
	@PutMapping("/user/cart/update")
	public String updateQuantity(@RequestBody String data) throws Exception {
		ObjectMapper obj = new ObjectMapper();
		// arr[0]: cartItemId; arr[1]: quantity update
		String[] arr = obj.readValue(data, String[].class);
		int cartItemId = Integer.parseInt(arr[0]);
		int quantity = Integer.parseInt(arr[1]);
		
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId()); //user.getUserId()
		Cart cart = user.getCustomer().getCart();
		List<CartItem> cartItems  = cart.getCartItem();
		for (CartItem cartItem : cartItems) {
			if(cartItem.getCartItemId()==cartItemId) {
				cartItem.setQuantity(quantity);
			}
		}
		cartService.save(cart);
		cart = cartService.getById(cart.getCartId());
		return "";
	}
	@PutMapping("/user/cart/delete")
	public String delProduct(ModelMap modelMap, @RequestBody String data) {
		//System.out.println("data: " + data);
		int cartItemId = Integer.parseInt(data);
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId()); //user.getUserId()
		Cart cart = user.getCustomer().getCart();
		List<CartItem> cartItems  = cart.getCartItem();
		for (CartItem cartItem : cartItems) {
			if(cartItem.getCartItemId()==cartItemId) {
				cartItemService.deleteById(cartItemId);
			}
		}
		cart = cartService.getById(cart.getCartId());
		return "";
	}
	
}
