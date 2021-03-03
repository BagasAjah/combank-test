package com.exam.comwelbank.controller;

import com.exam.comwelbank.entity.EmployeeEntity;
import com.exam.comwelbank.exception.CustomException;
import com.exam.comwelbank.model.EmployeeRequestPayload;
import com.exam.comwelbank.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public List<EmployeeEntity> fetchAll() {
        return employeeService.fetchAll();
    }

    @PostMapping
    public String addEmployee(@RequestBody EmployeeRequestPayload employeeRequestPayload) throws CustomException {
        final String SUCCESS_STRING = " success inserted !";
        employeeService.save(employeeRequestPayload);
        return employeeRequestPayload.getName() + SUCCESS_STRING;
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestPayload employeeRequestPayload) throws CustomException {
        final String SUCCESS_STRING = " success updated !";
        employeeService.update(employeeRequestPayload);
        return employeeRequestPayload.getName() + SUCCESS_STRING;
    }

    @DeleteMapping("{name}")
    public String deleteEmployee(@PathVariable String name) throws CustomException {
        final String SUCCESS_STRING = " success deleted !";
        employeeService.delete(name);
        return name + SUCCESS_STRING;
    }
}