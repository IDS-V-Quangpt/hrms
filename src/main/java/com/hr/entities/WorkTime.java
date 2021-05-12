package com.hr.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worktime")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class WorkTime {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// Mã N.Viên
	@Column(columnDefinition = "empno")
	private String empNo;
	
	// Tên N.Viên
	@Column(columnDefinition = "name")
	private String name;
	
	// Chức vụ
	@Column(columnDefinition = "position")
	private String position;
	
	// Phòng ban
	@Column(columnDefinition = "department")
	private String department;
	
	// Ngày
	@Column(columnDefinition = "date_worktime")
	private Date date_Worktime;
	
	// Thứ
	@Column(columnDefinition = "week_time")
	private String weekTime;
	
	// Ra
	@Column(columnDefinition = "check_in")
	private String checkIn;
	
	// Vào
	@Column(columnDefinition = "check_out")
	private String checkOut;
	
	// Công
	@Column(columnDefinition = "number_jobs")
	private Double numberJobs;
	
	// Giờ
	@Column(columnDefinition = "hours")
	private Double hours;
	
	// Công+
	@Column(columnDefinition = "number_jobs_plus")
	private Double numberJobsPlus;
	
	// Giờ+
	@Column(columnDefinition = "hours_plus")
	private Double hoursPlus;
	
	// Vào trễ
	@Column(columnDefinition = "late_entry")
	private Double lateEntry;
	
	// Ra sớm
	@Column(columnDefinition = "out_early")
	private Double outEarly;
	
	// TC1
	@Column(columnDefinition = "TC1")
	private Double tc1;
	
	// TC2
	@Column(columnDefinition = "TC2")
	private Double tc2;

	// TC3
	@Column(columnDefinition = "TC3")
	private Double tc3;
	
	// Tên ca
	@Column(columnDefinition = "name_shift")
	private String nameShift;
	
	// Ký hiệu
	@Column(columnDefinition = "symbol")
	private String symbol;
	
	// Ký hiệu+
	@Column(columnDefinition = "symbol_plus")
	private String symbolPlus;
	
	// Tổng cộng
	@Column(columnDefinition = "total")
	private Double total;
	
	// Ghi chú
	@Column(columnDefinition = "note")
	private String note;
}

