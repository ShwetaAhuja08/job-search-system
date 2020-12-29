package com.cg.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteJobsDO {
	
	public FavoriteJobsDO(Integer jobSeekerId) {
		super();
		this.jobSeekerId = jobSeekerId;
	}
	private Integer jobId;
	private Integer jobSeekerId;

}
