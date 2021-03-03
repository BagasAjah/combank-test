package com.exam.comwelbank.repo;

import com.exam.comwelbank.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findByName(String name);
    int deleteByName(String name);
}
