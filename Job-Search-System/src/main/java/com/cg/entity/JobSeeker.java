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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="jobseekers")
public class JobSeeker {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id",nullable=false)
	private Integer jobSeeker_Id;
	
	@NotNull
	@Size(min=2, message="Jobseeker name should have minimum 2 charactres")
	@Column(name= "name",nullable=false)
	private String jobSeeker_Name;
	
	@NotNull(message="Addressshould not be null")
	@Column(name= "address",nullable=false)
	private String address;
	
	@Column(name= "contact_no",nullable=false)
	private Long contact_No;
	
	@NotBlank(message="Email should not be blank")
	@Email
	@Column(name= "email",nullable=false)
	private String mail_ID;
	
	@NotNull
	@Column(name= "skill_set",nullable=false)
	private String skillSet;
	
	@NotNull
	@Column(name= "location_preference",nullable=false)
	private String location_preference;
	
	@NotNull
	@Size(min=5, message="Username should contain atleast 5 characters")
	@Column(name= "username",nullable=false)
	private String username;
	
	@NotNull
	@Size(min=6, max=16, message="Password should contain atleast 5 characters")
	@Column(name= "password",nullable=false)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobseeker")
	private Collection<AppliedJobs> appliedjobs = new ArrayList<AppliedJobs>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobseeker")
	private Collection<Message> message = new ArrayList<Message>();
	
	public JobSeeker(String jobSeeker_Name) {
		super();
		this.jobSeeker_Name = jobSeeker_Name;
	}
	
	
		
}