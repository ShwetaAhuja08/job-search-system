package com.cg.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name="Message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Message {

	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name= "description", nullable=false)
	private String description;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="jobseeker_id", nullable = false)
	private JobSeeker jobseeker;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="job_id", nullable= false)
	private Job job ;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="employer_id", nullable=false)
	private Employer employer;
	@Column(name="date",nullable=false)
	private Date date;

	public Message(String description) {
		this.description = description;
	}
}
