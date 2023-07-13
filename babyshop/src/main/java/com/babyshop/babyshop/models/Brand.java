package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.util.Status;

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
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id")
	private int brandId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String desciption;
	
	@Column(name = "status")
	private String status ;
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image image;
	
	//mappedBy: thuộc tính chỉ ra bên tham chiếu tới (brand ở đây là 1 trường trong product)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "brand", orphanRemoval = true)
	private List<Product> products;

	public Brand(String name, String desciption, Image image) {
		this.name = name;
		this.desciption = desciption;
		this.image = image;
	}
	
	public void setUpdateAtCurrentTime() {
		updateAt = new Timestamp(new Date().getTime());
	}
	public String getUriImage() {
		if(image!=null) {
			String imageName = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
		              "readDetailFileBrand", image.getName()).build().toUri().toString();
				return imageName;
		}
		return "";
	}

}
