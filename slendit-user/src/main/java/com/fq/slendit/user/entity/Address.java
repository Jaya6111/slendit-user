package com.fq.slendit.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "UserId is required")
	@Min(value = 1, message = "UserId must be a positive number")
	private int userId;

	@Column
	private String streetAddress;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String zip;
	@Column
	private String country;
	@Column
	private String type;

	@Column
	private LocalDateTime created;
	@Column
	private LocalDateTime updated;

}
