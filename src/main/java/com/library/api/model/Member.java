package com.library.api.model;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class Member {

	private Integer id;

	@NotNull(message = "Please provide Name.")
	private String name;

	@NotNull(message = "Please provide User Name.")
	private String username;

	@NotNull(message = "Please provide Password.")
	private String password;

	@NotEmpty(message = "Please provide Address.")
	private String address;

	@NotEmpty(message = "Please provide email.")
	@Email
	private String email;

	@Pattern(regexp = "^\\d{10}$")
	private String phone;

	@Value("Active")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
