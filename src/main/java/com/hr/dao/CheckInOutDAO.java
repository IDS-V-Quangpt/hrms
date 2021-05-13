package com.hr.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hr.config.Constants;
import com.hr.dto.response.ListCheckOutResponse;
import com.hr.entities.msdb.CheckInOut;
 
@Repository
public class CheckInOutDAO {
  
 
    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
    private EntityManager entityManager;
 
    public List<ListCheckOutResponse> getListCheckInOut() {
    	String sql = "SELECT U.UserLastName, U.UserFullName, C.TimeStr FROM UserInfo AS U INNER JOIN CheckInOut AS C ON u.UserEnrollNumber = C.UserEnrollNumber";
//        String sql = "Select e from " + Advertiser.class.getName() + " e ";
        Query query = entityManager.createNativeQuery(sql);
        
        @SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
        
        List<ListCheckOutResponse> re = new ArrayList<>();
        
        for (Object object : result) {
        	
        	if (object instanceof ListCheckOutResponse) {
        		ListCheckOutResponse res = (ListCheckOutResponse) object;
        		re.add(res);
        	}
		}
        
        
        return re;
    }
 
    public CheckInOut findById(Integer id) {
        return this.entityManager.find(CheckInOut.class, id);
    }
     
}