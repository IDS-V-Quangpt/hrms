package com.hr.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hr.dto.response.WorkTimeResponse;
import com.hr.entities.WorkTime;
import com.hr.interfaces.WorkTimeInterface;
import com.hr.repository.WorkTimeRepositories;
import com.hr.utils.ExcelHelper;

@Service
public class WorkTimeExcelService implements WorkTimeInterface {

	@Autowired
	private WorkTimeRepositories workTimeRepositories;

	@Override
	@Transactional
	public boolean saveWorkTime(MultipartFile file) {
		
		try {
			List<WorkTime> listWorkTimeResponse = ExcelHelper.excelToWorkTime(file.getInputStream());
			workTimeRepositories.saveAll(listWorkTimeResponse);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public WorkTimeResponse getAllWorkTime() {
		
		List<WorkTime> listWorkTime =  workTimeRepositories.findAll();
		WorkTimeResponse response = new WorkTimeResponse();
		response.setDraw(1);
		response.setRecordsFiltered(workTimeRepositories.count());
		response.setRecordsTotal(workTimeRepositories.count());
		response.setData(listWorkTime);
		
		return response;
		
	}
}
