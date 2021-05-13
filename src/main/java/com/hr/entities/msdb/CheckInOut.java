package com.hr.entities.msdb;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
 
@Entity
@Table(name = "CheckInOut")
@Data
public class CheckInOut implements Serializable {
 
    private static final long serialVersionUID = 746237126088051312L;
 
    @Id
    @Column(name = "UserEnrollNumber")
    private Integer userEnrollNumber;
 
    @Column(name = "timeStr")
    private Timestamp timeStr;
    
    @Column(name = "TimeDate")
    private Timestamp timeDate;
    
    @Column(name = "OriginType")
    private String originType;
    
    @Column(name = "NewType")
    private String newType;
    
    @Column(name = "Source")
    private Timestamp source;
    
    @Column(name = "machineNo")
    private Integer machineNo;
    
    @Column(name = "WorkCode")
    private Integer workCode;
}