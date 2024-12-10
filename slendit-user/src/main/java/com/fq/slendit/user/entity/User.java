package com.fq.slendit.user.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String email;
	private String userName;
	private String password;
	private String mobile;
	
	private String firstName;
	private String middleName;
	private String lastName;

	private String otp;
	private String phNoVerify;
	private String verificationCode;
	
	private String isEmailVerified;
	private String mobileVerified;
	private String role;
	
	private String aboutMe;

	private String blockStatus;
	private String blockedBy;
	private String blockedDate;
	private String isAddressAdded;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	private int numberOfItemsUploaded;

	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Set<Address> addresses;

	@OneToMany(targetEntity = Wishlist.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private List<Wishlist> wishList;

	@OneToOne(targetEntity = UserPicture.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private UserPicture userPicture;

}
