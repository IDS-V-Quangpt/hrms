package com.hr.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hr.dto.response.WorkTimeResponse;
import com.hr.entities.WorkTime;

public interface WorkTimeInterface {

	boolean saveWorkTime(MultipartFile file);
	WorkTimeResponse getAllWorkTime();
}
