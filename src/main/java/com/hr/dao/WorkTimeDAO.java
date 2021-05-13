package com.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hr.config.Constants;
import com.hr.entities.mysql.WorkTime;
 
@Repository
public class WorkTimeDAO {
 
    @Autowired
    @PersistenceContext( unitName= Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;
 
    @SuppressWarnings("unchecked")
	public List<WorkTime> listPublishers() {
        String sql = "Select e from " + WorkTime.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, WorkTime.class);
        return query.getResultList();
    }
 
    public WorkTime findById(Long id) {
        return this.entityManager.find(WorkTime.class, id);
    }
     
}