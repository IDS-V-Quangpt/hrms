package com.hr.dto.response;

import java.io.Serializable;
import java.util.List;

import com.hr.entities.WorkTime;

import lombok.Data;

@Data
public class WorkTimeResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<WorkTime> data;
}
