package com.cg.service;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppliedJobsDO {
	
	private Integer jobId;
	private Integer jobSeekerId;
	public AppliedJobsDO(Integer jobSeekerId) {
	
		this.jobSeekerId = jobSeekerId;
	}
	public AppliedJobsDO() {
		
	}
	
	
}
