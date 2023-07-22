package com.babyshop.babyshop.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;
import com.babyshop.babyshop.util.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
@Where(clause = "status != 'LOCK'")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "password")
	private String password;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "status")
	private String status = Status.UNLOCK;
	
	@Column(name = "gender")
	private boolean gender;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());

	@Column(name = "update_at")
	private Timestamp updateAt = new Timestamp(new java.util.Date().getTime());

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	List<Role> roles = new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image image;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().
				map(role ->new SimpleGrantedAuthority(role.getName())).toList();
	}

	public String getUriAvatar() {
		String uri;
		if (this.image != null) {
			uri = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "readDetailFileAvatar", image.getName()).build().toUri()
					.toString();
		}else {
			uri = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "readDetailFileAvatar", "avatar_default.png").build().toUri()
					.toString();
		}
		return uri;
	}
	public String getfullNameIfExistOrNot() {
		if(this.fullName==null) {
			return "Your Name";
		}else {
			return this.fullName;
		}
	}
	public String getDateOfBirth() {
	    if (this.dob != null) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); // "2023-6-20"
	        String formattedDate = this.dob.format(formatter);
	        return formattedDate;
	    }
	    return null;
	}
	
	public String getCreatedAt() {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d h:mm a");// "2023-6-20 2:33PM"
	        String formattedDate = sdf.format(createdAt);
	        return formattedDate;
	}

	@Override
	public String getUsername() {
		if(this.email!=null) return this.email;
		return this.phoneNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

