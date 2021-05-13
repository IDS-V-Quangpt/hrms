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

	public List<CheckOutMSResponse> getListCheckInOut() {

		String sql = "SELECT DISTINCT " + "U.UserLastName, " + "U.UserFullName, " + "C.TimeStr " + "FROM UserInfo AS U "
				+ "INNER JOIN CheckInOut AS C " + "ON u.UserEnrollNumber = C.UserEnrollNumber"
				+ " WHERE C.TimeStr >=:startDate AND C.TimeStr <=:endDate ORDER BY C.TimeStr ASC ";

		Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", DateUtils.firstDayOfMonth());
        query.setParameter("endDate", DateUtils.lastDayOfMonth());

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
	
		List<CheckOutMSResponse> res2 = results.stream().filter(distinctByKey(p -> p.getDate())).collect(Collectors.toList());
		
		List<CheckOutMSResponse> dtoMain = new ArrayList<>();
		for (CheckOutMSResponse checkOutMSResponse : res2) {
			
			Date maxDate = results.stream().filter(x -> x.getDate().equals(checkOutMSResponse.getDate())).map(u -> u.getDateConvert()).max(Date::compareTo).get();
			Date minDate = results.stream().filter(x -> x.getDate().equals(checkOutMSResponse.getDate())).map(u -> u.getDateConvert()).min(Date::compareTo).get();
			
			CheckOutMSResponse dto2 = new CheckOutMSResponse();
			dto2.setEmpNo(checkOutMSResponse.getEmpNo());
			dto2.setName(checkOutMSResponse.getName());
			dto2.setDate(checkOutMSResponse.getDate());
			dto2.setWeek(DateUtils.getWeek(checkOutMSResponse.getDate()));
			dto2.setCheckIn(DateUtils.getHourAndMinute(minDate));
			dto2.setCheckOut(DateUtils.getHourAndMinute(maxDate));
			dtoMain.add(dto2);
		}

		return dtoMain;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}