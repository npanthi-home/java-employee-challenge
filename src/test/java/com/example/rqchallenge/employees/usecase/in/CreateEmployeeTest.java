package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeTest {
    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private CreateEmployee createEmployee;

    @Test
    public void shouldCreateEmployee() {
        Employee employee = new Employee(1L, "John Doe", 50000, 30, "profile.jpg");
        when(employeeGateway.create(ArgumentMatchers.any(Employee.class))).thenReturn(Optional.of(employee));
        Optional<Employee> result = createEmployee.apply(employee);
        verify(employeeGateway, times(1)).create(employee);
        assertEquals(Optional.of(employee), result);
    }
}