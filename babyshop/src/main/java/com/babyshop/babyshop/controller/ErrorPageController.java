package com.babyshop.babyshop.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

//@Controller
////@RequestMapping("${server.error.path:${error.path:/error}}")
//public class ErrorPageController  implements ErrorController{
//	 @RequestMapping("/error_page")
//	    public String handleError(HttpServletRequest request) {
////	        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
////	        if(statusCode != null) {
////	        	if (statusCode == HttpStatus.SC_NOT_FOUND) {
////		            // Xử lý trang lỗi 404
////		            return "redirect:/";
////		        } else {
////		            // Xử lý các trang lỗi khác
////		            return "redirect:/error";
////		        }
////	        }
//		 return "redirect:/";
//	    }
//}
