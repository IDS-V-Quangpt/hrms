package com.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hr.dto.response.WorkTimeResponse;
import com.hr.interfaces.WorkTimeInterface;
import com.hr.messages.ResponseMessage;
import com.hr.utils.ExcelHelper;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

	@Autowired
	private WorkTimeInterface workTime;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> getWorkTime(@RequestBody @RequestParam("file") MultipartFile file) {

		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				if(workTime.saveWorkTime(file)) {
					message = "Uploaded the file successfully: " + file.getOriginalFilename();
					return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
				} else {
					message = "Could not upload the file: " + file.getOriginalFilename() + "!";
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
				}
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@CrossOrigin(origins = {"http://localhost:8087", "http://localhost:8089"})
	@PostMapping(value = "/worktime", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkTimeResponse> listWorkTime() {
		
		WorkTimeResponse result = workTime.getAllWorkTime();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
