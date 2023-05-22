package com.babyshop.babyshop.models;

//Class trạng thái trả về 
public class ResponseObject {
	private String status;
	private String message;
	private Object data;// data có thể có rất nhiều loại nên để kiểu object
						//cha của mọi kiểu dữ liệu
	public ResponseObject() {
		
	}
	public ResponseObject(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
