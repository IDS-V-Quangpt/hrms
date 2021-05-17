package com.hr.dao;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hr.config.Constants;
import com.hr.dto.response.CheckOutMSResponse;
import com.hr.utils.DateUtils;

@Repository
public class CheckInOutDAO {

	@Autowired
	@PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
	private EntityManager entityManager;

	/**
	 * Get list CheckIn-Out
	 * 
	 * @return List CheckOutMSResponse
	 */
	public List<CheckOutMSResponse> getListCheckInOut() {

		String sql = "SELECT DISTINCT " + "U.UserLastName, " + "U.UserFullName, " + "C.TimeStr " + "FROM UserInfo AS U "
				+ "INNER JOIN CheckInOut AS C " + "ON u.UserEnrollNumber = C.UserEnrollNumber"
				+ " WHERE C.TimeStr >= :startDate AND C.TimeStr <= :endDate ORDER BY C.TimeStr ASC ";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("startDate", DateUtils.firstDayOfMonth(null));
		query.setParameter("endDate", DateUtils.lastDayOfMonth(null));

		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<CheckOutMSResponse> results = new ArrayList<>();
		for (Object[] objects : result) {

			CheckOutMSResponse dto = new CheckOutMSResponse();
			dto.setEmpNo(objects[0] == null ? null : (String) objects[0]);
			dto.setName(objects[1] == null ? null : (String) objects[1]);

			Timestamp ts = (Timestamp) objects[2];
			dto.setDate(objects[2] == null ? null : DateUtils.formatTimeStamp(ts.getTime()));
			dto.setDateConvert(ts);

			DayOfWeek week = DateUtils.getWeek(DateUtils.formatTimeStamp(ts.getTime()));
			dto.setWeek(week);

			results.add(dto);
		}

		List<CheckOutMSResponse> distinctResponse = results.stream().filter(distinctByKey(p -> p.getEmpNo()))
				.collect(Collectors.toList());

		List<CheckOutMSResponse> dtoMain = new ArrayList<>();

		for (CheckOutMSResponse checkOutMSResponse : distinctResponse) {

			Date minDate = getMaxAndMin(false, checkOutMSResponse.getName(), checkOutMSResponse.getDateConvert());
			Date maxDate = getMaxAndMin(true, checkOutMSResponse.getName(), checkOutMSResponse.getDateConvert());

			CheckOutMSResponse resonseMain = new CheckOutMSResponse();
			resonseMain.setEmpNo(checkOutMSResponse.getEmpNo());
			resonseMain.setName(checkOutMSResponse.getName());
			resonseMain.setDate(checkOutMSResponse.getDate());
			resonseMain.setWeek(DateUtils.getWeek(checkOutMSResponse.getDate()));
			resonseMain.setCheckIn(DateUtils.getHourAndMinute(minDate));
			resonseMain.setCheckOut(DateUtils.getHourAndMinute(maxDate));
			resonseMain.setTotal(DateUtils.formatDuration(DateUtils.getHourAndMinute(minDate), DateUtils.getHourAndMinute(maxDate)));
			resonseMain.setPaidLeave(DateUtils.calPaidLeave(DateUtils.formatDuration(DateUtils.getHourAndMinute(minDate), DateUtils.getHourAndMinute(maxDate))));
			resonseMain.setHours(DateUtils.hoursExtend(DateUtils.formatDuration(DateUtils.getHourAndMinute(minDate), DateUtils.getHourAndMinute(maxDate))));
			dtoMain.add(resonseMain);
		}

		return dtoMain;
	}

	/**
	 * Distinct Data
	 * 
	 * @param <T>
	 * @param keyExtractor
	 * @return
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	/**
	 *  Get Checkin, CheckOut
	 *  
	 * @param flag
	 * @param name
	 * @param time
	 * @return
	 */
	private Timestamp getMaxAndMin(boolean flag, String name, Timestamp time) {

		String startDate = DateUtils.firstDayOfMonth(time);
		String endDate = DateUtils.lastDayOfMonth(time);

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT\r\n");
		if (flag == false) {
			sb.append("	MIN(C.`TimeStr`)\r\n");
		} else {
			sb.append("	MAX(C.`TimeStr`)\r\n");
		}
		sb.append("	FROM `UserInfo` AS U\r\n");
		sb.append("INNER JOIN `CheckInOut` AS C ON\r\n");
		sb.append("	u.`UserEnrollNumber` = C.`UserEnrollNumber`\r\n");
		sb.append("WHERE\r\n");
		sb.append("	U.`UserFullName` = :name\r\n");
		sb.append("	AND C.`TimeStr` >= :startDate\r\n");
		sb.append("	AND C.`TimeStr` <= :endDate\r\n");
		sb.append("GROUP BY\r\n");
		sb.append("	U.`UserLastName`,\r\n");
		sb.append("	U.`UserFullName`");

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("name", name);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		return (Timestamp) result.get(0);
	}

}