package com.app.pojos;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

  @Entity

  @NoArgsConstructor
  
  @AllArgsConstructor
  
  @Getter
  
  @Setter

  @ToString
 
public class Users extends BaseEntity{
	@Column(length = 30)
	private String userName;
	@Column(length = 30, unique = true) // unique constraint
	private String userEmail;
	@JsonProperty(access=Access.WRITE_ONLY)
	private String password;
	@Column(length = 10)
	private String mobileNumber;
	private String address;
	
	/*
	 * public void setPassword(String password) { Base64.Encoder encoder =
	 * Base64.getEncoder(); String normalString = password; String encodedString =
	 * encoder.encodeToString( // encrypt password in database field
	 * normalString.getBytes(StandardCharsets.UTF_8) ); this.password =
	 * encodedString; }
	 */
	
	

}
