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
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int imageId;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;

	public Image(String imageName) {
		this.imageName = imageName;
	}
	
	
}
