package com.hr.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmployeeResponse {

	@NotNull(message = "Mã số nhân viên")
	private String userCodeID;
	@NotNull(message = "Tên nhân viên")
	private String fullName;
	private Boolean married;
	private int numberChild;
}
