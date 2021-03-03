package com.exam.comwelbank.service;

import com.exam.comwelbank.entity.EmployeeEntity;
import com.exam.comwelbank.exception.CustomException;
import com.exam.comwelbank.model.EmployeeRequestPayload;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> fetchAll();
    EmployeeEntity save(EmployeeRequestPayload requestPayload) throws CustomException;
    EmployeeEntity update(EmployeeRequestPayload requestPayload) throws CustomException;
    void delete(String name) throws CustomException;
}
