package com.babyshop.babyshop.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@Column(name = "specification")
	private String specification;

	@Column(name = "brands_id")
	private int brandId;

	@Column(name = "discount")
	private double discount;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;
	
	@Transient
	private double salePrice;
	
	@Transient
	private List<Image> images;
	
	@Transient
	private String categoryName;
	
	public Product(String name, double price, String description, String specification, int brandId, Date createdAt,
			Date updateAt) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.specification = specification;
		this.brandId = brandId;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public double getSalePrice() {
		return price - price*discount;
	}
	public int getDiscount() {
		return (int)(discount*100);
	}
	public int getPrice() {
		return (int)price;
	}
	public List<Image> getImages(){
		if(images==null) images = new ArrayList<>();
		return images;
	}
}
