package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private int feedbackId;
	
	@Column(name = "rate_star")
	private int rateStar = 0;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "likes")
	private int likes = 0;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_details_id")
	private OrderDetails orderDetails;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product = new Product();
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinTable(
            name = "image_feedback",
            joinColumns = {@JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "image_id")}
    )
	private List<Image> images;
	
	@Column(name = "status")
	private String status = Status.INACTIVE;
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new java.util.Date().getTime());
	
	public String getTime() {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d h:mm a");// "2023-6-20 2:33PM"
	        String formattedDate = sdf.format(createdAt);
	        return formattedDate;
	}
	
	public List<String> getUriImages(){
		if(images!=null) {
			List<String> uriImages = new ArrayList<>();
			for (Image image : images) {
				String imageName = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "readDetailFileFeedback", image.getName()).build().toUri()
				.toString();
				uriImages.add(imageName);
			}
			return uriImages;
		}
		return new ArrayList<>();
	}

}
