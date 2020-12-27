 package com.cg.entity;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
		@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name="title", nullable = false)
	private String title;
	@Column(name="location", nullable = false)
	private String location;
	@Column(name="description", nullable = false)
	private String description;
	@Column(name="experience", nullable = false)
	private String experience;
	@Column(name="salary", nullable = false)
	private double salary;
	@Column(name="noticePeriod", nullable = false)
	private String noticePeriod;
	@Column(name="status", nullable = false)
	private String status;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="contactNo", nullable = false)
	private long contactNo;
	@Column(name="skillSet", nullable = false)
	private String skillSet;
	//@JsonIgnore
	//@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="emp_id")
	private Employer employer;
	//@JsonIgnore
	//@ToString.Exclude
	@Column(name="jobseeker_id", nullable = false)
	private int jobseeker_id;
	@JsonIgnore
	//@ToString.Exclude
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private Collection<AppliedJobs> appliedjobs = new ArrayList<AppliedJobs>();
	@JsonIgnore
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private Collection<Message> message = new ArrayList<Message>();
	@JsonIgnore
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private Collection<FavoriteJobs> favJobs = new ArrayList<FavoriteJobs>();
	public Job(String title) {
		super();
		this.title = title;
	}

	
	
//	@Override
//	public String toString() {
//		return "Job [id=" + id + ", title=" + title + ", location=" + location + ", description=" + description
//				+ ", experience=" + experience + ", salary=" + salary + ", noticePeriod=" + noticePeriod + ", status="
//				+ status + ", email=" + email + ", contactNo=" + contactNo + ", skillSet=" + skillSet + ", employer="
//				+ employer.getId() + ", jobseeker_id=" + jobseeker_id + ", appliedjobs=" + appliedjobs + "]";
//	}

}