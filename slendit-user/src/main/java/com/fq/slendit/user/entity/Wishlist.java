package com.fq.slendit.user.entity;

import java.util.Date;

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
	private String userId;
	@Column
	private String productName;
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private Date created;
	@Column
	private Date updated;
}
