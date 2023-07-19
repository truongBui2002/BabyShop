package com.babyshop.babyshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.babyshop.babyshop.models.Customer;
import com.babyshop.babyshop.models.Order;
import com.babyshop.babyshop.models.OrderDetails;
import com.babyshop.babyshop.models.User;
import com.babyshop.babyshop.service.OrderDetailsService;
import com.babyshop.babyshop.service.UserService;
import com.babyshop.babyshop.util.Status;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderHistoryController {
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderDetailsService orderDetailsService; 
	
	@GetMapping("/user/viewprofile/order/orderhistory/all")
	public String allOrder(ModelMap modelMap) {
		User user = (User)session.getAttribute("user"); //để lại dòng này
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		List<OrderDetails> odDetails = new ArrayList<>();
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
		return "response/orderhistory";
	}
	@GetMapping("/user/viewprofile/order/orderhistory/wait")
	public String waitOrder(ModelMap modelMap) {
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		List<OrderDetails> odDetails = new ArrayList<>();
		if(customer!=null) {
			
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails()); 
			}
			if(odDetails.size()!=0) {
				orderDetailsService.sortByTime(odDetails);
				odDetails = odDetails.stream().filter(od -> od.getStatus().equals(Status.WAIT)).toList();
			}
		}
		modelMap.addAttribute("odDetails", odDetails);
		
		return "response/orderhistory";
	}
	@GetMapping("/user/viewprofile/order/orderhistory/shipping")
	public String shippingOrder(ModelMap modelMap) {
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		List<OrderDetails> odDetails = new ArrayList<>();
		if(customer!=null) {
			
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails()); 
			}
			if(odDetails.size()!=0) {
				orderDetailsService.sortByTime(odDetails);
				odDetails = odDetails.stream().filter(od -> od.getStatus().equals(Status.SHIP)).toList();
			}
		}
		modelMap.addAttribute("odDetails", odDetails);
		return "response/orderhistory";
	}
	@GetMapping("/user/viewprofile/order/orderhistory/completed")
	public String completedOrder(ModelMap modelMap) {
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		List<OrderDetails> odDetails = new ArrayList<>();
		if(customer!=null) {
			
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails()); 
			}
			if(odDetails.size()!=0) {
				orderDetailsService.sortByTime(odDetails);
				odDetails = odDetails.stream().filter(od -> od.getStatus().equals(Status.COMPLETED)).toList();
			}
		}
		modelMap.addAttribute("odDetails", odDetails);
		return "response/orderhistory";
	}
	@GetMapping("/user/viewprofile/order/orderhistory/cancelled")
	public String cancelledOrder(ModelMap modelMap) {
		User user = (User)session.getAttribute("user");
		user = userService.getUserById(user.getUserId());// 
		Customer customer = user.getCustomer();
		List<OrderDetails> odDetails = new ArrayList<>();
		if(customer!=null) {
			
			List<Order> orders = customer.getOrders();
			for (Order order : orders) {
				odDetails.addAll(order.getOrderDetails()); 
			}
			if(odDetails.size()!=0) {
				orderDetailsService.sortByTime(odDetails);
				odDetails = odDetails.stream().filter(od -> od.getStatus().equals(Status.CANCELLED)).toList();
			}
		}
		modelMap.addAttribute("odDetails", odDetails);
		return "response/orderhistory";
	}
}
