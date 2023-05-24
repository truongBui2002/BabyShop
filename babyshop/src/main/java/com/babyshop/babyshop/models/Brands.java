package com.babyshop.babyshop.models;

import java.util.Date;

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
@Table(name = "brands")
public class Brands {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brands_id")
	private int brandId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image_id")
	private int imageId;
	
	@Column(name = "description")
	private String desciption;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "update_at")
	private Date updateAt;
	
	@Transient
	private String imageName;
	
	public Brands(String name, int imageId, String desciption, Date createdAt, Date updateAt) {
		this.name = name;
		this.imageId = imageId;
		this.desciption = desciption;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	
	
}
