package com.babyshop.babyshop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.Location;
import com.babyshop.babyshop.models.Order;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.models.Variant;
import com.babyshop.babyshop.service.CustomerService;
import com.babyshop.babyshop.service.LocationService;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.service.VariantService;
import com.babyshop.babyshop.util.Status;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserRestController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	OrderDetailsService odDetailsService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	VariantService variantService;
	
	@PutMapping("/user/viewprofile/address/address-make-default")
	public String addressMakeDefault(@RequestBody String locationId) {
		int locaId = Integer.parseInt(locationId);
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());//
		Customer customer = user.getCustomer();

		List<Location> locations = customer.getLocations();

		boolean checkExists = false;
		for (Location location : locations) {
			if (location.getLocationId()==locaId) {
				checkExists = true;
			}
		}
		if (checkExists) {
			for (Location location : locations) {
				if (location.getLocationId()==locaId) {
					location.setStatus(Status.DEFAULT);
				}
				else {
					location.setStatus(Status.NON_DEFAULT);
				}
			}
		}
		customerService.save(customer);
		return "";
	}
	
	@PutMapping("/user/viewprofile/address/address-remove")
	public String addressRemove(@RequestBody String locationId) {
		int locaId = Integer.parseInt(locationId);
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());//
		Customer customer = user.getCustomer();
		List<Location> locations = customer.getLocations();
		for (Location loca : locations) {
			if (loca.getLocationId()==locaId) {
				loca.setStatus(Status.HIDDEN);
			}
		}
		customerService.save(customer);
		return "";
	}
	
	@PutMapping("/user/viewprofile/order/orderhistory/cancel")
	public String cancelProduct(@RequestBody String data) {
		int oddid = Integer.parseInt(data);
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());//
		Customer customer = user.getCustomer();
		List<Order> orders = customer.getOrders();
		OrderDetails odDetails = null;
		for (Order order : orders) {
			List<OrderDetails> orderDetails = order.getOrderDetails();
			for (OrderDetails oddt : orderDetails) {
				if(oddt.getOrderDetailsId()==oddid) {
					odDetails = oddt;
				}
			}
		}
		if(odDetails!=null) {
			// hoàn lại số lượng sản phẩm trong kho sau khi hủy đơn hàng 
			Variant variant = odDetails.getVariant();
			variant.setQuantity(variant.getQuantity() + odDetails.getQuantity());
			variantService.save(variant);
			odDetails.setStatus(Status.CANCELLED);
			odDetailsService.save(odDetails);
			return Status.CANCELLED;
		}
		
		return "Cancel";
	}
	
	@PutMapping("/user/viewprofile/order/orderhistory/confirm-recive")
	public String confirmReceive(@RequestBody String data) {
		int oddid = Integer.parseInt(data);
		User user = (User) session.getAttribute("user"); // để lại dòng này
		user = userService.getUserById(user.getUserId());//
		Customer customer = user.getCustomer();
		List<Order> orders = customer.getOrders();
		OrderDetails odDetails = null;
		for (Order order : orders) {
			List<OrderDetails> orderDetails = order.getOrderDetails();
			for (OrderDetails oddt : orderDetails) {
				if(oddt.getOrderDetailsId()==oddid) {
					odDetails = oddt;
				}
			}
		}
		if(odDetails!=null) {
			odDetails.setStatus(Status.COMPLETED);
			odDetailsService.save(odDetails);
			return Status.COMPLETED;
		}
		return "Receive";
	}
}
