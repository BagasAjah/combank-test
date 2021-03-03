package com.exam.comwelbank.controller;

import com.exam.comwelbank.entity.EmployeeEntity;
import com.exam.comwelbank.service.PyramidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pyramid")
@AllArgsConstructor
public class PyramidController {
    private final PyramidService pyramidService;

    @GetMapping("/{totalRows}")
    public String printPyramidOnConsole(@PathVariable int totalRows) {
        pyramidService.printPyramid(totalRows);
        return "OK";
    }
}
