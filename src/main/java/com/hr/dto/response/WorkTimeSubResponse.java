package com.hr.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class WorkTimeSubResponse {

	private String empNo;
	private String name;
	private Date date_Worktime;
	private String checkIn;
	private String checkOut;
	private Double numberJobs;
	private Double hours;
	private Double numberJobsPlus;
	private Double lateEntry;
	private Double outEarly;
	private String nameShift;
	private Double total;
	private String note;
}
