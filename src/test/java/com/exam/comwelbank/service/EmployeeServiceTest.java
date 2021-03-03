package com.exam.comwelbank.service;

import com.exam.comwelbank.entity.EmployeeEntity;
import com.exam.comwelbank.exception.CustomException;
import com.exam.comwelbank.model.EmployeeRequestPayload;
import com.exam.comwelbank.repo.EmployeeRepo;
import com.exam.comwelbank.service.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void fetchAll_shouldReturnListOfEmployee_whenMethodInvoked() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        List<EmployeeEntity> employeeEntities = Arrays.asList(employeeEntity);
        when(employeeRepo.findAll()).thenReturn(employeeEntities);

        List<EmployeeEntity> actualReturn = employeeService.fetchAll();
        assertEquals(1, actualReturn.size());
    }

    @Test
    public void save_shouldReturnEntityObject_whenSaveDatasuccess() throws CustomException {
        EmployeeRequestPayload employeeRequestPayload = EmployeeRequestPayload.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(employeeRequestPayload.getName())
                .phone(employeeRequestPayload.getPhone())
                .build();
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(null);
        when(employeeRepo.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        EmployeeEntity resultObject = employeeService.save(employeeRequestPayload);

        verify(employeeRepo).findByName(ArgumentMatchers.anyString());
        assertEquals(resultObject.getName(), employeeRequestPayload.getName());
    }

    @Test
    public void save_shouldThrowError_whenSaveAlreadyExist() {
        EmployeeRequestPayload employeeRequestPayload = EmployeeRequestPayload.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(employeeRequestPayload.getName())
                .phone(employeeRequestPayload.getPhone())
                .build();
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(employeeEntity);

        try {
            employeeService.save(employeeRequestPayload);
        } catch (CustomException exception) {
            final String EXPECTED_MESSAGE = "Data Already exist !";
            assertEquals(EXPECTED_MESSAGE, exception.getMessage());
        }
    }

    @Test
    public void update_shouldReturnEntityObject_whenUpdateDataSuccess() throws CustomException {
        EmployeeRequestPayload employeeRequestPayload = EmployeeRequestPayload.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(employeeRequestPayload.getName())
                .phone(employeeRequestPayload.getPhone())
                .build();
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(employeeEntity);
        when(employeeRepo.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        EmployeeEntity resultObject = employeeService.update(employeeRequestPayload);

        verify(employeeRepo).findByName(ArgumentMatchers.anyString());
        assertEquals(resultObject.getName(), employeeRequestPayload.getName());
    }

    @Test
    public void update_shouldThrowError_whenUpdateDataNotExist() {
        EmployeeRequestPayload employeeRequestPayload = EmployeeRequestPayload.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(null);

        try {
            employeeService.update(employeeRequestPayload);
        } catch (CustomException exception) {
            final String EXPECTED_MESSAGE = "Data not exist !";
            assertEquals(EXPECTED_MESSAGE, exception.getMessage());
        }
    }

    @Test
    public void delete_shouldExecuteDeleteMethod_whenDeleteDataSuccess() throws CustomException {
        final String NAME = "Name";
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name("Dummy Name")
                .phone("08990")
                .build();
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(employeeEntity);

        employeeService.delete(NAME);

        verify(employeeRepo).deleteByName(ArgumentMatchers.anyString());
    }

    @Test
    public void delete_shouldThrowError_whenDeleteDataNotExist() {
        final String NAME = "Name";
        when(employeeRepo.findByName(ArgumentMatchers.anyString())).thenReturn(null);

        try {
            employeeService.delete(NAME);
        } catch (CustomException exception) {
            final String EXPECTED_MESSAGE = "Data not exist !";
            assertEquals(EXPECTED_MESSAGE, exception.getMessage());
        }
    }

}
