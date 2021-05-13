package com.hr.dto.response;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class ListCheckOutResponse {

	private String userLastName;
	private String userFullName;
	private Timestamp timeStr;
	
	public ListCheckOutResponse() {
		
	}
	
	public ListCheckOutResponse(String lastName, String fullName, Timestamp time) {
		this.userLastName = lastName;
		this.userFullName = fullName;
		this.timeStr = time;
	}
}
