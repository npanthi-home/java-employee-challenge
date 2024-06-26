package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllEmployeesTest {
    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private GetAllEmployees getAllEmployees;

    @Test
    public void shouldGetAllEmployees() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png")
        );

        when(employeeGateway.getAll()).thenReturn(mockEmployees);
        List<Employee> result = getAllEmployees.get();
        verify(employeeGateway, times(1)).getAll();
        assertEquals(mockEmployees, result);
    }
}