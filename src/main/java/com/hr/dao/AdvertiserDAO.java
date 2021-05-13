package com.hr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hr.config.Constants;
import com.hr.entities.entity2.Advertiser;
 
@Repository
public class AdvertiserDAO {
  
 
    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
    private EntityManager entityManager;
 
    public List<Advertiser> listAdvertisers() {
        String sql = "Select e from " + Advertiser.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, Advertiser.class);
        return query.getResultList();
    }
 
    public Advertiser findById(Long id) {
        return this.entityManager.find(Advertiser.class, id);
    }
     
}