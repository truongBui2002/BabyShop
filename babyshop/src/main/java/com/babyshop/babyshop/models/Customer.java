package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "full_name")
	private String fullName;
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	private User user; 
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private  List<Location> locations = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Feedback> feedbacks;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Cart cart;
	
	@Column(name = "status")
	private String status;
	 
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new java.util.Date().getTime());
	
}

