package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_info")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_info_id")
	private int productInfoId;
	
	@Column(name = "age_range")
	private String ageRange;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "style")
	private String style = "";
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());
	
	@OneToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	private Product product;
}
