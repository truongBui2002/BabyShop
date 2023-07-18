package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "cart_id")
	private int cartId;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> cartItem = new ArrayList<>();
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());
}
