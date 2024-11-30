package com.fq.slendit.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlockUser  {
	
	@Id
	private String id;
	
	private String productId;
	private String blockedUser;
	private String blockedBy;
	private Date created;
	private Date updated;
	private String status;
	private String version;
	
}
