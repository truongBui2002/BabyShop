package com.babyshop.babyshop.util;

public final class Status {
	 public static final String UNLOCK = "UNLOCK"; //Cho phép truy cập
	 public static final String LOCK = "LOCK"; // Chặn truy cập
	 public static final String COMPLETED = "COMPLETED";// trạng thái hoàn thành
	 public static final String SHIP = "SHIP";// đang vận chuyển
	 public static final String WAIT = "WAIT";//chờ xác nhận
	 public static final String CANCELLED = "CANCELLED";//người dùng hủy đơn hàng
	 public static final String ACTIVE = "ACTIVE";//hoạt động (kích hoạt)
	 public static final String INACTIVE = "INACTIVE";//không hoạt động (hủy)
	 public static final String DEFAULT = "DEFAULT";// thiết lập default cho địa chỉ 
	 public static final String NON_DEFAULT = "NON_DEFAULT";// thiết lập default cho địa chỉ 
}
