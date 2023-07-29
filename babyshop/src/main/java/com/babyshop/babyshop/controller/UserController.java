package com.babyshop.babyshop.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.babyshop.babyshop.models.Feedback;
import com.babyshop.babyshop.models.Image;
import com.babyshop.babyshop.models.Location;
import com.babyshop.babyshop.models.Order;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.CartItemService;
import com.babyshop.babyshop.service.CartService;
import com.babyshop.babyshop.service.CustomerService;
import com.babyshop.babyshop.service.FeedbackService;
import com.babyshop.babyshop.service.FirebaseService;
import com.babyshop.babyshop.service.ImageService;
import com.babyshop.babyshop.service.LocationService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.SendMailService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.service.VariantService;
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

	@Autowired
	FirebaseService firebaseService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	FeedbackService feedbackService;

	@Autowired
	VariantService variantService;

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

	@PostMapping("/user/viewprofile/newpass")
	public String newPass(ModelMap modelMap, @RequestParam(name = "new-password") String pass,
			@RequestParam(name = "re-new-password") String re_pass) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (pass.equals(re_pass)) {
				user.setPassword(pass);
				user.setStatus(Status.UNLOCK);
				userService.saveUserEncoder(user);
				return "redirect:/user/viewprofile/changepass?success=Successfully generated new password";
			} else {
				return "redirect:/user/viewprofile/changepass?error=Re-enter password does not match";
			}
		} else {
			return "redirect:/user/viewprofile/changepass?error=Not Found User";
		}
	}

	@PostMapping("/user/viewprofile/changepass")
	public String changePass(ModelMap modelMap, @RequestParam(name = "password") String pass,
			@RequestParam(name = "new-password") String newPass,
			@RequestParam(name = "re-new-password") String re_pass) {
		User user = (User) session.getAttribute("user");
		user = userService.getUserById(user.getUserId());
		if (user != null) {

			if (passwordEncoder.matches(pass, user.getPassword())) {
				if (newPass.equals(re_pass)) {
					user.setPassword(newPass);

					userService.saveUserEncoder(user);
					return "redirect:/user/viewprofile/changepass?success=Successfully generated new password";
				} else {
					return "redirect:/user/viewprofile/changepass?error=Re-enter password does not match";
				}
			} else {
				return "redirect:/user/viewprofile/changepass?error=Old password is incorrect";
			}
		} else {
			return "redirect:/user/viewprofile/changepass?error=Not Found User";
		}
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

	@PostMapping("/register/phone")
	public String registerByPhone(@RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
			@RequestParam(name = "p-password", defaultValue = "") String password, ModelMap modelMap,
			@RequestBody String data) {
		String tokenPhone = (String) session.getAttribute("tokenPhone");
		if (tokenPhone != null) {
			String phoneNumberByToken = firebaseService.getPhoneNumberByToken(tokenPhone);
			if (phoneNumberByToken != null) {
				String regexPhone = phoneNumber.replaceFirst("^0", "+84");
				if (phoneNumberByToken.equals(regexPhone)) {
					User user = new User();
					user.setPhoneNumber(phoneNumber);
					user.setPassword(password);
					userService.saveUser(user);
					return "redirect:/login?success";
				}

			}
		}
		return "redirect:/login?err";

	}

	@PostMapping("/forgot/phone")
	public String forgotByPhone(ModelMap modelMap,
			@RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
			@RequestParam(name = "e-password", defaultValue = "") String password, @RequestBody String data) {
		String tokenPhone = (String) session.getAttribute("tokenPhone");
		if (tokenPhone != null) {
			String phoneNumberByToken = firebaseService.getPhoneNumberByToken(tokenPhone);
			if (phoneNumberByToken != null) {
				String regexPhone = phoneNumber.replaceFirst("^0", "+84");
				if (phoneNumberByToken.equals(regexPhone)) {
					User user = userService.findByPhone(phoneNumber);
					if (user != null) {
						user.setPhoneNumber(phoneNumber);
						user.setPassword(password);
						userService.saveUserEncoder(user);
						return "redirect:/login?success";
					}

				}

			}
		}
		return "redirect:/login?err";
	}

	@PostMapping("/register/email")
	public String registerByEmail(@RequestParam(name = "emailHidden", defaultValue = "") String email,
			@RequestParam(name = "e-password", defaultValue = "") String password, ModelMap modelMap,
			@RequestBody String data) {
		String emailConfirm = (String) session.getAttribute("emailConfirm");
		if (emailConfirm != null) {
			if (emailConfirm.equals(email)) {
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				userService.saveUser(user);
			}
		}
		return "redirect:/login?success";
	}

	@PostMapping("/user/update/profile")
	public String updateProfile(@RequestParam(name = "avatar") MultipartFile avatar,
			@RequestParam(name = "day") String day, @RequestParam(name = "month") String month,
			@RequestParam(name = "year") String year, @RequestParam(name = "fullName") String fullName,
			@RequestParam(name = "gender") String gender) {
		User user = (User) session.getAttribute("user");
		user = userService.getUserById(user.getUserId());
		if (!avatar.isEmpty()) {
			String imgName = imageService.saveAvatar(avatar);
			if (user.getImage() != null) {
				user.getImage().setName(imgName);
			} else {
				Image image = new Image(imgName);
				user.setImage(image);
			}
		}

		int newYear = Integer.parseInt(year);
		int newMonth = Integer.parseInt(month);
		int newDay = Integer.parseInt(day);
		try {
			LocalDate localDate = LocalDate.of(newYear, newMonth, newDay);
			user.setDob(localDate);
		} catch (DateTimeException e) {
			e.printStackTrace();
		}
		user.setFullName(fullName);
		if (gender.equals("male")) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		userService.save(user);
		user = userService.getUserById(user.getUserId());
		session.setAttribute("user", user);
		return "redirect:/user/viewprofile";
	}

	@GetMapping("/user/update/email")
	public String updateEmail(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		return "email";
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
			userService.saveUserEncoder(user);
			modelMap.addAttribute("success", "Password sent to your email!!!");
		} else {
			modelMap.addAttribute("err", "Email does not exist!!!");
		}
		return "forgotbyemail";
	}

	@GetMapping("/user/purchase")
	public String purchase(ModelMap modelMap, @RequestParam(name = "cartItemId") List<Integer> cartItemsId) {
		loadDataController.loadData(modelMap);
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());// user.getUserId()
		Customer customer = user.getCustomer();
		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getCartItem();
		List<CartItem> purchaseItem = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			for (Integer cartItemId : cartItemsId) {
				if (cartItem.getCartItemId() == cartItemId && cartItem.getVariant().getQuantity() > 0) {
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
		User user = (User) session.getAttribute("user"); // để lại dòng này
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

					// Cập nhật lại số lượng sản phẩm trong kho, tổng trong kho - số lượng mua
					Variant variant = variantService.getVariantById(cartItem.getVariant().getVariantId());
					variant.setQuantity(variant.getQuantity() - cartItem.getQuantity());
					variantService.save(variant);
					cartItemService.deleteById(cartItemId);
				}
			}
		}

		customerService.save(customer);

		return "redirect:/user/viewprofile/order/orderhistory";
	}

	@GetMapping("/user/viewprofile/address")
	public String changeAddress(ModelMap modelMap) {
		loadDataController.loadData(modelMap);
		User user = (User) session.getAttribute("user"); // để lại dòng này
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
		List<Location> locations = customer.getLocations();
		locations = locations.stream().filter(location -> !location.getStatus().equals(Status.HIDDEN)).toList();
		modelMap.addAttribute("customer", customer);
		modelMap.addAttribute("locations", locations);
		modelMap.addAttribute("color", "address");
		return "/profile/changeaddress";
	}

	@PostMapping("/user/viewprofile/address/add")
	public String addAddress(ModelMap modelMap, @RequestParam(name = "full-name") String fullName,
			@RequestParam(name = "phone-number") String phoneNumer, @RequestParam(name = "address") String address,
			@RequestBody String data) {
		loadDataController.loadData(modelMap);
		// System.out.println(data);

		User user = (User) session.getAttribute("user"); // để lại dòng này
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
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());//
		Customer customer = user.getCustomer();
		if (customer != null) {
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails());
			}
			if (odDetails.size() != 0) {
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

	@PostMapping("/user/feedback-product")
	public String feedBackProduct(@RequestParam(name = "odDetailsId", defaultValue = "") String oddId,
			@RequestParam(name = "rating", defaultValue = "") String rate,
			@RequestParam(name = "feedback-image", defaultValue = "") List<MultipartFile> fileImages,
			@RequestParam(name = "feedback-description", defaultValue = "") String description) {

		int odDetailsId = Integer.parseInt(oddId);
		int rateStar = Integer.parseInt(rate);
		OrderDetails orderDetails = orderDetailsService.getById(odDetailsId);
		List<Image> images = new ArrayList<>();
		if (orderDetails != null) {
			Feedback feedback = orderDetails.getFeedback();
			if (feedback == null) {
				feedback = new Feedback();
				feedback.setOrderDetails(orderDetails);
				feedback.setRateStar(rateStar);
				feedback.setDescription(description);
				feedback.setCustomer(orderDetails.getOrder().getCustomer());
				feedback.setProduct(orderDetails.getProduct());
				feedback.setLikes(orderDetails.getOrderDetailsId());
				if (!fileImages.get(0).isEmpty()) {
					for (MultipartFile file : fileImages) {
						String imageName = imageService.saveImageFeedback(file);
						Image image = new Image();
						image.setName(imageName);
						images.add(image);
					}
				}
				if (images.size() != 0) {
					feedback.setImages(images);
				}
				feedbackService.save(feedback);

			}

		}
//		System.out.println("oddDetailsId: " + oddId);
//		System.out.println("rate: " + rate);
//		System.out.println("images: " + fileImages.size());
//		System.out.println("description: " + description);

		return "redirect:/user/viewprofile/order/orderhistory";
	}

}
