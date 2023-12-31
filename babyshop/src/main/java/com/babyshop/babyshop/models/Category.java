package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;

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
import lombok.NoArgsConstructor;
@Scope("protoype")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image image;

	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Subcategory> subcategories;

	public Category(String name) {
		this.name = name;
	}
	
	public String getUriImage() {
		if(image!=null) {
			String imageName = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "readDetailFileCategory", image.getName()).build().toUri()
					.toString();
					return imageName;
		}
		return "";
	}
}
