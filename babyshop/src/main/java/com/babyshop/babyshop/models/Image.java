 package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Scope("protoype")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int imageId;
	
	@Column(name = "name")
	private String name;
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());
	
	public Image(String name) {
		this.name = name;
	}
	
}
