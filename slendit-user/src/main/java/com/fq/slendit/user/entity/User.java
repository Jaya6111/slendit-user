package com.fq.slendit.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private int id;

	@Column
	private String email;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String mobile;

	@Column
	private String firstName;
	@Column
	private String middleName;
	@Column
	private String lastName;

	@Column
	private String otp;
	@Column
	private String phNoVerify;
	@Column
	private String verificationCode;
	@Column
	private String emailVerify;
	@Column
	private String role;

	@Column
	private String blockStatus;
	@Column
	private String blockedBy;
	@Column
	private Date blockedDate;
	@Column
	private String isAddressAdded;
	@Column
	private Date created;
	@Column
	private Date updated;

	@Column
	private int numberOfItemsUploaded;

	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "userId")
	private Set<Address> addresses;

	@OneToMany(targetEntity = Wishlist.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "userId")
	private List<Wishlist> wishList;

	@OneToOne(targetEntity = UserPicture.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "userId")
	private UserPicture userPicture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPhNoVerify() {
		return phNoVerify;
	}

	public void setPhNoVerify(String phNoVerify) {
		this.phNoVerify = phNoVerify;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getEmailVerify() {
		return emailVerify;
	}

	public void setEmailVerify(String emailVerify) {
		this.emailVerify = emailVerify;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public String getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}

	public Date getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(Date blockedDate) {
		this.blockedDate = blockedDate;
	}

	public String getIsAddressAdded() {
		return isAddressAdded;
	}

	public void setIsAddressAdded(String isAddressAdded) {
		this.isAddressAdded = isAddressAdded;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getNumberOfItemsUploaded() {
		return numberOfItemsUploaded;
	}

	public void setNumberOfItemsUploaded(int numberOfItemsUploaded) {
		this.numberOfItemsUploaded = numberOfItemsUploaded;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Wishlist> getWishList() {
		return wishList;
	}

	public void setWishList(List<Wishlist> wishList) {
		this.wishList = wishList;
	}

	public UserPicture getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(UserPicture userPicture) {
		this.userPicture = userPicture;
	}

}
