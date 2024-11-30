package com.fq.slendit.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	private String id;
	private String userId;

	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String type;
	
	private String addressData;
	
}
