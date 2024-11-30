package com.fq.slendit.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wishlist {

	@Id
	private String id;

	private String userId;
	private String productName;
	private String userName;
	private String userEmail;
	private Date created;
	private Date updated;
}
