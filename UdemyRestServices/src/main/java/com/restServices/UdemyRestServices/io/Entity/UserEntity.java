package com.restServices.UdemyRestServices.io.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -4915286087296230761L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false , length=50)
	private String firstName;
	
	@Column(nullable=false , length=50)
	private String lastName;
	
	@Column(nullable=false , length=150, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private Boolean emailVerificationToken=false;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(Boolean emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public String getEmailVerificationStatus() {
	return emailVerificationStatus;
}
	public void setEmailVerificationStatus(String emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	@Column(columnDefinition="Boolean")
	private String emailVerificationStatus;
	
}
