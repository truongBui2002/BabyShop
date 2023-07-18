package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babyshop.babyshop.models.Cart;
import com.babyshop.babyshop.models.CartItem;
import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Location;
import com.babyshop.babyshop.models.Order;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.CartItemService;
import com.babyshop.babyshop.service.CartService;
import com.babyshop.babyshop.service.CustomerService;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.LocationService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.RandomKey;
import com.babyshop.babyshop.util.Status;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private LoadDataController loadDataController;

	@Autowired
	HttpSession session;

	@Autowired
	SendMailService sendMailService;

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	CartService cartService;

	@Autowired
	LocationService locationService;
	
	@Autowired
	OrderDetailsService orderDetailsService;

	@GetMapping("/login")
	public String login(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "login";
	}

	@GetMapping("/forgotpassword")
	public String forgotPass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotpass";
	}

	@GetMapping("/forgotbyemail")
	public String forgotbyemail(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotbyemail";
	}

	@GetMapping("/forgotbyphone")
	public String forgotbyphone(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "forgotbyphone";
	}

	@GetMapping("/user/viewprofile")
	public String viewProfile(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		
		modelMap.addAttribute("color", "profile");
		return "viewprofile";
	}

	@GetMapping("/email")
	public String email(ModelMap modelMap) {

		return "email";
	}

	@GetMapping("/phone")
	public String phone(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "phone";
	}

	@GetMapping("/customer")
	public String customer() {
		return "test";
	}

	@PostMapping("/register/phone")
	public String registerByPhone(@RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
			@RequestParam(name = "p-password", defaultValue = "") String password, ModelMap modelMap,
			@RequestBody String data) {
		User user = new User();
		System.out.println(data);
		System.out.println("Phone Number: " + phoneNumber);
		System.out.println("Password " + password);
//		user.setPhoneNumber(phoneNumber);
//		user.setPassword(password);
//		userService.saveUser(user);

		return "redirect:/login?success";
	}

	@PostMapping("/register/email")
	public String registerByEmail(@RequestParam(name = "rgt-email", defaultValue = "") String email,
			@RequestParam(name = "e-password", defaultValue = "") String password, ModelMap modelMap,
			@RequestBody String data) {
		User user = new User();
		System.out.println(data);
		System.out.println("Phone Number: " + email);
		System.out.println("Password " + password);
		user.setEmail(email);
		user.setPassword(password);
		userService.saveUser(user);

		return "redirect:/login?success";
	}

	@PostMapping("/user/update/profile")
	public String updateProfile(@RequestParam(name = "avatar") MultipartFile avatar,
			/*
			 * @RequestParam(name = "day") String day, @RequestParam(name = "month") String
			 * month,
			 */
			/* @RequestParam(name = "year") String year, */ @RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "address") String address, @RequestParam(name = "gender") String gender) {
		User user = (User) session.getAttribute("user");
		if (!avatar.isEmpty()) {
			String imgName = imageService.updateAvatar(avatar);
			if (user.getImage() != null) {
				user.getImage().setName(imgName);
			} else {
				Image image = new Image(imgName);
				user.setImage(image);
			}
		}
		/*
		 * int newYear = Integer.parseInt(year); int newMonth = Integer.parseInt(month);
		 * int newDay = Integer.parseInt(day);
		 */
		/*
		 * Date date = new Date(newYear, newMonth, newDay); user.setDob(date);
		 */

		user.setFullName(firstName);
		if (gender.equals("1")) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		userService.saveUser(user);

		return "redirect:/user/viewprofile";
	}

	@GetMapping("/user/update/email")
	public String updateEmail(ModelMap modelMap) {
		loadDataController.loadData(modelMap);

		return "email";
	}

	@GetMapping("/user/resetpass")
	public String resetPass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "resetpass";
	}

	@PostMapping("/user/update/password")
	public String updatePass(ModelMap modelMap, @RequestParam(name = "password") String password,
			@RequestParam(name = "repass") String repass) {
		loadDataController.loadData(modelMap);
		if (password.equals(repass)) {
			User user = (User) session.getAttribute("user");
			user.setPassword(password);
			userService.saveUser(user);
			return "redirect:/user/resetpass?success";
		} else {
			modelMap.addAttribute("err", true);
			return "redirect:/user/resetpass?err";
		}
	}

	@PostMapping("/fogotpass/email")
	public String fogotByEmail(ModelMap modelMap, @RequestParam(name = "email") String email) {
		loadDataController.loadData(modelMap);
		User user = userService.findByEmail(email);
		if (user != null) {
			RandomKey rd = new RandomKey();
			String pass = rd.getString(10);
			sendMailService.sendNewPass(email, pass);
			user.setPassword(pass);
			userService.updatePass(user);
			modelMap.addAttribute("success", "Password sent to your email!!!");
		} else {
			modelMap.addAttribute("err", "Email does not exist!!!");
		}
		return "forgotbyemail";
	}

	@GetMapping("/user/purchase")
	public String purchase(ModelMap modelMap, @RequestParam(name = "cartItemId") List<Integer> cartItemsId) {
		loadDataController.loadData(modelMap);
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// user.getUserId()
		Customer customer = user.getCustomer();
		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getCartItem();
		List<CartItem> purchaseItem = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			for (Integer cartItemId : cartItemsId) {
				if (cartItem.getCartItemId() == cartItemId) {
					purchaseItem.add(cartItem);
				}
			}
		}
		int totalAmount = 0;
		for (CartItem cartItem : purchaseItem) {
			totalAmount += cartItem.getQuantity() * cartItem.getVariant().getProduct().getSalePrice();
		}
		modelMap.addAttribute("purchaseItem", purchaseItem);
		modelMap.addAttribute("customer", customer);
		modelMap.addAttribute("totalAmount", totalAmount);
		return "purchase";
	}

	@GetMapping("/user/purchase/order")
	public String order(ModelMap modelMap,
			@RequestParam(name = "cartItemId", defaultValue = "") List<Integer> cartItemsId,
			@RequestParam(name = "locationId", defaultValue = "") String locationId) {
		loadDataController.loadData(modelMap);
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// user.getUserId()
		Customer customer = user.getCustomer();
		Cart cart = customer.getCart();

		List<Order> orders = customer.getOrders();
		Order order = new Order();
		order.setCustomer(customer);
		orders.add(order);
		Location location = locationService.getById(Integer.parseInt(locationId));
		order.setLocation(location);

		List<OrderDetails> orderDetails = order.getOrderDetails();

		// Tạo list những item vừa mua
		List<CartItem> cartItems = cart.getCartItem();
		for (Integer cartItemId : cartItemsId) {
			for (CartItem cartItem : cartItems) {
				if (cartItem.getCartItemId() == cartItemId) {
					OrderDetails odDetails = new OrderDetails();
					odDetails.setOrder(order);
					odDetails.setProduct(cartItem.getVariant().getProduct());
					odDetails.setPrice(cartItem.getVariant().getProduct().getSalePrice());
					odDetails.setVariant(cartItem.getVariant());
					odDetails.setQuantity(cartItem.getQuantity());
					orderDetails.add(odDetails);
					cartItemService.deleteById(cartItemId);
				}
			}
		}

		// Xóa Item trong cart đã mua
		customerService.save(customer);

		return "redirect:/user/viewprofile/order/orderhistory";
	}

	@GetMapping("/user/viewprofile/address")
	public String changeAddress(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// user.getUserId()

		Customer customer = user.getCustomer();
		if (customer == null) {
			if (customer == null) {
				customer = new Customer();
				customer.setUser(user);
				user.setCustomer(customer);
				userService.save(user);
				// cập nhật user khi user thay đổi
				user = userService.getUserById(user.getUserId());
				customer = user.getCustomer();
			}
		}
		modelMap.addAttribute("customer", customer);
		modelMap.addAttribute("color", "address");
		return "/profile/changeaddress";
	}

	@PostMapping("/user/viewprofile/address/add")
	public String addAddress(ModelMap modelMap, @RequestParam(name = "full-name") String fullName,
			@RequestParam(name = "phone-number") String phoneNumer, @RequestParam(name = "address") String address,
			@RequestBody String data) {
		loadDataController.loadData(modelMap);
		// System.out.println(data);

		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		customer.setFullName(fullName);
		List<Location> locations = customer.getLocations();
		Location location = new Location();
		location.setPhoneNumber(phoneNumer);
		location.setAddress(address);
		location.setCustomer(customer);
		boolean checkDefault = false;
		for (Location loca : locations) {
			if (loca.getStatus().equals(Status.DEFAULT)) {
				checkDefault = true;
			}
		}
		if (!checkDefault) {
			location.setStatus(Status.DEFAULT);
		} else {
			location.setStatus(Status.NON_DEFAULT);
		}
		locations.add(location);
		customerService.save(customer);
		customer = user.getCustomer();

		modelMap.addAttribute("customer", customer);
		return "redirect:/user/viewprofile/address";
	}
	
	@GetMapping("/user/viewprofile/order/orderhistory")
	public String orderhistory(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		List<OrderDetails> odDetails = new ArrayList<>();
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		if(customer!=null) {
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails());
			}
			if(odDetails.size()!=0) {
				orderDetailsService.sortByTime(odDetails);
			}
		}
		modelMap.addAttribute("odDetails", odDetails);
		return "/order/orderhistory";
	}
	

	@GetMapping("/user/viewprofile/changepass")
	public String changepass(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		
		modelMap.addAttribute("color", "changepass");
		return "/profile/changepass";
	}

	@GetMapping("/user/viewprofile/payment")
	public String proPayment(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		
		modelMap.addAttribute("color", "payment");
		return "/profile/profilepayment";
	}

	@GetMapping("/user/viewprofile/noti")
	public String proNoti(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "/profile/notification";
	}

	
}
