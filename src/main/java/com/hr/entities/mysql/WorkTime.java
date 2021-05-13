package com.hr.entities.mysql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Table(name = "worktime")
@Data
public class WorkTime implements Serializable {
 
    private static final long serialVersionUID = 746237126088051312L;
 
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "position")
	private String position;
    
    @Column(name = "department")
	private String department;
    
    @Column(name = "date_worktime")
	private java.util.Date date_worktime;
    
    @Column(name = "week_time")
	private String week_time;
    
    @Column(name = "check_in")
	private String check_in;
    
    @Column(name = "check_out")
	private String check_out;
    
    @Column(name = "number_jobs")
	private double number_jobs;
    
    @Column(name = "hours")
	private String hours;
    
    @Column(name = "number_jobs_plus")
	private String number_jobs_plus;
    
    @Column(name = "hours_plus")
	private String hours_plus;
    
    @Column(name = "late_entry")
	private double late_entry;
    
    @Column(name = "out_early")
	private double out_early;
    
    @Column(name = "TC1")
	private String TC1;
    
    @Column(name = "TC2")
	private String TC2;
    
    @Column(name = "TC3")
	private String TC3;
    
    @Column(name = "name_shift")
	private String name_shift;
    
    @Column(name = "symbol")
	private String symbol;
    
    @Column(name = "symbol_plus")
	private String symbol_plus;
    
    @Column(name = "total")
	private String total;
    
    @Column(name = "note")
	private String note;
}
