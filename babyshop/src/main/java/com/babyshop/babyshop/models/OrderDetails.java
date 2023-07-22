package com.babyshop.babyshop.models;

import java.sql.Timestamp;

import com.babyshop.babyshop.util.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "order_details")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_details_id")
	private int orderDetailsId;
	
	@Column(name = "price")
	private double price;
	
	@OneToOne
	@JoinColumn(name = "variant_id")
	private Variant variant;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; 
	
	@Column(name = "profit")
	private String profit = "0";
	
	@Column(name = "discount")
	private double discount = 0;
	
	@OneToOne(mappedBy = "orderDetails", cascade = CascadeType.ALL)
	private Feedback feedback;
	
	@Column(name = "status")
	private String status = Status.WAIT;
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new java.util.Date().getTime());
}
