package com.hr.utils;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hr.entities.WorkTime;

public class ExcelHelper {

	private static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	private static String[] HEADERs = { "Mã N.Viên", "Tên N.Viên", "Phòng ban", "Chức vụ", "Ngày", "Thứ", "Vào", "Ra",
			"Công", "Giờ", "Công+", "Giờ+", "Vào trễ", "Ra sớm", "TC1", "TC2", "TC3", "Tên ca", "K.hiệu", "K.hiệu +",
			"Tổng giờ" };

	private static String SHEET = "Xuất lưới";

	public static boolean hasExcelExists(final String fileName) {

		final File file = new File(fileName);

		if (file.exists()) {
			return true;
		}

		return false;
	}

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<WorkTime> excelToWorkTime(InputStream inputStream) {

		List<WorkTime> listWorkTimeResponse = new ArrayList<>();
		
		try {

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			int rowNumber = 5;
			while (rows.hasNext()) {

				Row currentRow = rows.next();

				if (currentRow.getRowNum() == 0 || currentRow.getRowNum() == 1 || currentRow.getRowNum() == 2) {
					rowNumber++;

					continue;
				}
				// skip header
				if (rowNumber == 5) {
					rowNumber++;

					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				WorkTime workTime = new WorkTime();

				int cellIdx = 0;

				while (cellsInRow.hasNext()) {
					
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						workTime.setEmpNo((String) currentCell.getStringCellValue());
						break;
					case 1:
						workTime.setName((String) currentCell.getStringCellValue());
						break;
					case 2:
						workTime.setDepartment((String) currentCell.getStringCellValue());
						break;
					case 3:
						workTime.setPosition((String) currentCell.getStringCellValue().toString());
						break;
					case 4:
						workTime.setDate_Worktime((Date) currentCell.getDateCellValue());
						break;
					case 5:
						workTime.setWeekTime((String) currentCell.getStringCellValue());
						break;
					case 6:
						workTime.setCheckIn((String) currentCell.getStringCellValue());
						break;
					case 7:
						workTime.setCheckOut((String) currentCell.getStringCellValue());
						break;
					case 8:
						workTime.setNumberJobs((Double) currentCell.getNumericCellValue());
						break;
					case 9:
						workTime.setHours((Double) currentCell.getNumericCellValue());
						break;
					case 10:
						workTime.setNumberJobsPlus((Double) currentCell.getNumericCellValue());
						break;
					case 11:
						workTime.setHoursPlus((Double) currentCell.getNumericCellValue());
						break;
					case 12:
						workTime.setLateEntry((Double) currentCell.getNumericCellValue());
						break;
					case 13:
						workTime.setOutEarly((Double) currentCell.getNumericCellValue());
						break;
					case 14:
						workTime.setTc1((Double) currentCell.getNumericCellValue());
						break;
					case 15:
						workTime.setTc2((Double) currentCell.getNumericCellValue());
						break;
					case 16:
						workTime.setTc3((Double) currentCell.getNumericCellValue());
						break;
					case 17:
						workTime.setNameShift((String) currentCell.getStringCellValue());
						break;
					case 18:
						workTime.setSymbol((String) currentCell.getStringCellValue());
						break;
					case 19:
						workTime.setSymbolPlus((String) currentCell.getStringCellValue());
						break;
					case 20:
						workTime.setTotal((Double) currentCell.getNumericCellValue());
						break;
					default:
						break;
					}

					cellIdx++;

					listWorkTimeResponse.add(workTime);
				}
			}
			
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listWorkTimeResponse;
	}
	
	public static double withBigDecimal(double value, int places) {
	    BigDecimal bigDecimal = new BigDecimal(value);
	    bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
	    return bigDecimal.doubleValue();
	}
}
