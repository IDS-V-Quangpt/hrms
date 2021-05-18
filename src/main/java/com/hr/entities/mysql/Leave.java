package com.hr.entities.mysql;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "leave_day")
@Data
public class Leave implements Serializable {

	private static final long serialVersionUID = 746237126088051312L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "leave_day")
	private Date leaveDay;

	@Column(name = "leave_from")
	private Float leaveFrom;

	@Column(name = "leave_to")
	private Float leaveTo;

	@Column(name = "user_comment")
	private String userComment;

	@Column(name = "approve")
	private Integer approve;

	@Column(name = "approve_user_id")
	private Integer approveUserId;

	@Column(name = "approve_comment")
	private String approveComment;

}
