package com.fq.slendit.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	private String id;

	private String email;
	private String password;
	private String phoneNo;
	private String displayName;
	private MultipartFile picture;
	private String pictureDimension;

	private String picURL;
	private String firstName;
	private String lastName;

	private String facebookId;
	private String googleId;

	private String otp;
	private String phNoVerify;
	private String verificationCode;
	private String emailVerify;
	private String role;
	private String blockStatus;
	private String blockedBy;
	private Date blockedDate;
	private String isAddressAdded;
	private Date created;
	private Date updated;

	private int numberOfItemsUploaded;
	
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "userId")
	private Set<Address> addresses;
	
	@OneToMany(targetEntity = Wishlist.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "userId")
	private List<Wishlist> wishList;
}
