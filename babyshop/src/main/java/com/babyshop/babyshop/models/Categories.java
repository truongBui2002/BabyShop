package com.babyshop.babyshop.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;

	public Categories(int productId, String categoryName) {
		super();
		this.productId = productId;
		this.categoryName = categoryName;
	}
	
	
	
}
