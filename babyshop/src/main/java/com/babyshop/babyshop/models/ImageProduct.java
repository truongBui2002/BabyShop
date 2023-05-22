package com.babyshop.babyshop.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "image_product")
public class ImageProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_product_id")
	private int imageProductId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "image_id")
	private int imageId;
	
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;

	public ImageProduct(int productId, int imageId) {
		this.productId = productId;
		this.imageId = imageId;
	}
	
	
}
