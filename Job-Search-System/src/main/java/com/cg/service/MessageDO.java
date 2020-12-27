package com.cg.service;


import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDO {
	//private Integer id;
		private String description;
		private Integer jobseekerId;
		private Integer jobId ;
		private Integer employerId;
		private Date date;


}
