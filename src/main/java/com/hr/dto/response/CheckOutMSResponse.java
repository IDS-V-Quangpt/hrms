package com.hr.dto.response;


import java.io.Serializable;
import java.sql.Timestamp;

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
	private String week;
	private String checkIn;
	private String checkOut;
	private String total;
	private String paidLeave;
	private Boolean specialCase;
	private String workTime;
	private Double earlyLate;
	private Double from;
	private Double to;
	private String hours;
	private String note;
}
