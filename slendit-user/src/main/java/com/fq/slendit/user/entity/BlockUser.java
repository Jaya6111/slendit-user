package com.fq.slendit.user.entity;

import java.time.LocalDateTime;

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
public class BlockUser  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String productId;
	@Column
	private String blockedUser;
	@Column
	private String blockedBy;
	@Column
	private LocalDateTime created;
	@Column
	private LocalDateTime updated;
	@Column
	private String status;
	
}
