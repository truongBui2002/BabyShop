package com.babyshop.babyshop.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;

import com.babyshop.babyshop.util.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Scope("protoype")
@Data
@NoArgsConstructor
@AllArgsConstructor

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

	@Column(name = "discount")
	private double discount;
	
	@Column(name = "status")
	private String status = Status.UNLOCK;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "update_at")
	private Date updateAt;

	@Transient
	private double salePrice;
	// fetch = FetchType.EAGER
	// trong quá trình truy vấn thực thể "product" nó cũng sẽ tải các thực thể liên
	// quan(Eager Loading)

	// cascade = CascadeType.ALL
	// cho phép tự động lưu các thay đổi ở mức độ liên kết giữa các thực thể
	// (sửa xóa sẽ ảnh hướng tới thực thể liên quan)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinTable(
            name = "image_product",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            //join với cột ở một bảng khác
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "image_id")}
    )
	private List<Image> images;
	
	//fetch = FetchType.LAZY: được truy vấn khi gọi tới
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;
	
	

	public double getSalePrice() {
		return price - price*discount;
	}
	public int getDiscount() {
		return (int)(discount*100);
	}
	public int getPrice() {
		return (int)price;
	}
}
