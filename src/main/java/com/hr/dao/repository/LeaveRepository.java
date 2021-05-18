package com.hr.dao.repository;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.config.Constants;
import com.hr.entities.mysql.Leave;
 
@Repository
@PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
@Qualifier("PRIMARY_ENTITY_MANAGER_FACTORY")
public interface LeaveRepository extends JpaRepository<Leave, Integer>{
 
}