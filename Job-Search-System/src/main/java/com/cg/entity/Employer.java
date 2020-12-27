package com.cg.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity


@Table(name="Employer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employer {

	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name= "organization_Name", nullable = false)

	private String organizationName;
	@Column(name="address",nullable=false)
	private String address;
	@Column(name="contact_no",nullable=false)
	private long contactNo;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="username",nullable=false)
	private String username;
	@Column(name="password",nullable=false)
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
	private Collection<Job> job = new ArrayList<Job>();
	@JsonIgnore
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
	private Collection<Message> message = new ArrayList<Message>();
	
	public Employer(String organizationName) {
		this.organizationName = organizationName;
	}
	
	

}
