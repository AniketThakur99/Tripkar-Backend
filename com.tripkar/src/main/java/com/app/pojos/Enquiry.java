package com.app.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Enquiry extends BaseEntity{
	private String name;
	@Column(length=30 ,unique=true)
	private String email;
	private String subject;
	@Column(length=500)
	private String message;
	
	@ManyToOne
	Users user;
}
