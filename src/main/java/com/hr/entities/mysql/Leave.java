package com.hr.entities.mysql;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "leave")
@Data
public class Leave implements Serializable {

	private static final long serialVersionUID = 746237126088051312L;

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "leave_day")
	private Date leaveDay;

	@Column(name = "from")
	private Float from;

	@Column(name = "to")
	private Float to;

	@Column(name = "comment")
	private String comment;

	@Column(name = "approve")
	private Boolean approve;

	@Column(name = "approve_user_id")
	private Integer approveUserId;

	@Column(name = "approve_comment")
	private String approveComment;

	@Column(name = "is_delete")
	private Boolean IsDelete;
}
