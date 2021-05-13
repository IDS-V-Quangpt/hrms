package com.hr.dto.response;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.DayOfWeek;

import lombok.Data;

@Data
public class CheckOutMSResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empNo;
	private String name;
	private String date;
	private Timestamp dateConvert;
	private DayOfWeek week;
	private String checkIn;
	private String checkOut;
	private Double total;
	private Double paidLeave;
	private Boolean specialCase;
	private Double earlyLate;
	private Double from;
	private Double to;
	private Double hours;
	private String note;
	private String startDate;
	private String endDate;
}
