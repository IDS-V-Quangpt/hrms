package com.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entities.WorkTime;

@Repository
public interface WorkTimeRepositories extends JpaRepository<WorkTime, Long> {
}
