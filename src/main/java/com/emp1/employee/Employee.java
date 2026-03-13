package com.emp1.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employee {
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(max = 80, message = "Name must be at most 80 characters")
	private String name;

	@NotBlank(message = "Role is required")
	@Size(max = 80, message = "Role must be at most 80 characters")
	private String role;

	@NotBlank(message = "Qualification is required")
	@Size(max = 120, message = "Qualification must be at most 120 characters")
	private String qualification;

	@NotBlank(message = "Address is required")
	@Size(max = 250, message = "Address must be at most 250 characters")
	private String address;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	@Size(max = 120, message = "Email must be at most 120 characters")
	private String email;

	@Size(max = 250, message = "Resume link must be at most 250 characters")
	@Pattern(regexp = "^$|^https?://.+", message = "Resume link must start with http:// or https://")
	private String resume;

	@Size(max = 250, message = "Portfolio link must be at most 250 characters")
	@Pattern(regexp = "^$|^https?://.+", message = "Portfolio link must start with http:// or https://")
	private String portfolio;

	public Employee() {}

	public Employee(Long id, String name, String role, String qualification, String address, String email, String resume, String portfolio) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.qualification = qualification;
		this.address = address;
		this.email = email;
		this.resume = resume;
		this.portfolio = portfolio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
}

