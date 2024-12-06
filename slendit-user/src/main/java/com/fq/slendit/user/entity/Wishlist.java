package com.fq.slendit.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int userId;
	@Column
	private String productName;
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private String created;
	@Column
	private String updated;
}
