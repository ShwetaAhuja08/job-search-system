package com.cg.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
@Table(name="jobseeker")
public class JobSeeker {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id",nullable=false)
//	@OneToMany(mappedBy="jobseeker_id")
	private Integer jobSeeker_Id;
	@Column(name= "name",nullable=false)
	private String jobSeeker_Name;
	@Column(name= "address",nullable=false)
	private String address;
	@Column(name= "contact_no",nullable=false)
	private Long contact_No;
	@Column(name= "email",nullable=false)
	private String mail_ID;
	
	@Column(name= "skill_set",nullable=false)
	private String skillSet;
	@Column(name= "location_preference",nullable=false)
	private String location_preference;
	@Column(name= "username",nullable=false)
	private String username;
	@Column(name= "password",nullable=false)
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "jobseeker", cascade=CascadeType.ALL)
	private Collection<AppliedJobs> appliedjobs = new ArrayList<AppliedJobs>();
	@JsonIgnore
	@OneToMany(mappedBy = "jobseeker", cascade=CascadeType.ALL)
	private Collection<Message> message = new ArrayList<Message>();
	@JsonIgnore
	@OneToMany(mappedBy = "jobseeker", cascade=CascadeType.ALL)
	private Collection<FavoriteJobs> favoriteJobs = new ArrayList<FavoriteJobs>();
	
	public JobSeeker(String jobSeeker_Name) {
		this.jobSeeker_Name = jobSeeker_Name;
	}
		
}