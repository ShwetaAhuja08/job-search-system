package com.cg.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Employers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employer {

	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private Integer id;

	@NotNull
	@Size(min=5, message="Organization name should have atleast 5 characters")
	@Column(name= "organization_Name")
	private String organizationName;

	@NotBlank
	@Column(name="address",nullable=false)
	private String address;

	@Column(name="contact_no",nullable=false)
	private long contactNo;

	@NotBlank
	@Email
	@Column(name="email",nullable=false)
	private String email;

	@NotNull
	@Size(min=5, message="Username should contains minimum 5 characters")
	@Column(name="username",nullable=false)
	private String username;

	@NotNull
	@Size(min=6, max=16, message="Password should contains minimum 6 characters")
	@Column(name="password",nullable=false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private Collection<Job> job = new ArrayList<Job>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private Collection<Message> message = new ArrayList<Message>();
	
	public Employer(String organizationName) {
		this.organizationName = organizationName;
	}


}