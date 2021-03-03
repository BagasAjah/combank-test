package com.exam.comwelbank.service.impl;

import com.exam.comwelbank.entity.EmployeeEntity;
import com.exam.comwelbank.exception.CustomException;
import com.exam.comwelbank.model.EmployeeRequestPayload;
import com.exam.comwelbank.repo.EmployeeRepo;
import com.exam.comwelbank.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public List<EmployeeEntity> fetchAll() {
        return employeeRepo.findAll();
    }

    @Override
    public EmployeeEntity save(EmployeeRequestPayload requestPayload) throws CustomException {
        EmployeeEntity existingData = employeeRepo.findByName(requestPayload.getName());
        if (existingData != null) {
            throw new CustomException("Data Already exist !");
        }
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(requestPayload.getName())
                .phone(requestPayload.getPhone())
                .build();
        return employeeRepo.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(EmployeeRequestPayload requestPayload) throws CustomException {
        EmployeeEntity existingData = employeeRepo.findByName(requestPayload.getName());
        if (existingData == null) {
            throw new CustomException("Data not exist !");
        }
        existingData.setName(requestPayload.getName());
        existingData.setPhone(requestPayload.getPhone());
        return employeeRepo.save(existingData);
    }

    @Override
    @Transactional
    public void delete(String name) throws CustomException {
        EmployeeEntity existingData = employeeRepo.findByName(name);
        if (existingData == null) {
            throw new CustomException("Data not exist !");
        }
        employeeRepo.deleteByName(name);
    }
}
