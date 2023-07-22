package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.util.Status;

import org.checkerframework.common.returnsreceiver.qual.This;
import org.hibernate.annotations.Where;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Scope("protoype")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product")
@Where(clause = "status = 'UNLOCK'") // <> là dấu khác
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
	private Timestamp createdAt = new Timestamp(new Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new Date().getTime());

	@Transient
	private double salePrice = 0.0;
	// fetch = FetchType.EAGER
	// trong quá trình truy vấn thực thể "product" nó cũng sẽ tải các thực thể liên
	// quan(Eager Loading)

	// cascade = CascadeType.ALL
	// cho phép tự động lưu các thay đổi ở mức độ liên kết giữa các thực thể
	// (sửa xóa sẽ ảnh hướng tới thực thể liên quan)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "image_product", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "product_id") },
			// join với cột ở một bảng khác
			inverseJoinColumns = { @JoinColumn(name = "image_id", referencedColumnName = "image_id") })
	private List<Image> images = new ArrayList<>();

	// fetch = FetchType.LAZY: được truy vấn khi gọi tới
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id")
	private Subcategory subcategory;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private ProductInfo productInfo;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Variant> variants;

	@OneToMany(mappedBy = "product")
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "product")
	private List<OrderDetails> orderDetails;

	public int getSalePrice() {
		if (this.discount == 1)
			return (int) price;
		return (int) (price - price * discount);
	}

	public int getDiscount() {
		return (int) (discount * 100);
	}

	public int getPrice() {

		return (int) price;
	}

	public List<String> getUriImages() {
		if (images != null) {
			List<String> uriImages = new ArrayList<>();
			for (Image image : images) {
				String imageName = MvcUriComponentsBuilder
						.fromMethodName(ImageController.class, "readDetailFileProduct", image.getName()).build().toUri()
						.toString();
				uriImages.add(imageName);
			}
			return uriImages;
		}
		return new ArrayList<>();
	}

	@Transient
	private Brand brandExists;
	
	
}
