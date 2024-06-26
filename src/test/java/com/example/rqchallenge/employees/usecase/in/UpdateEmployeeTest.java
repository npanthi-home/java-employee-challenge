package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEmployeeTest {

    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private UpdateEmployee updateEmployee;

    @Test
    public void testApply() {
        Employee employeeToUpdate = new Employee(1L, "John Doe", 50000, 30, "profile.jpg");
        when(employeeGateway.update(employeeToUpdate)).thenReturn(Optional.of(1L));
        Optional<Long> result = updateEmployee.apply(employeeToUpdate);
        verify(employeeGateway, times(1)).update(employeeToUpdate);
        assertEquals(Optional.of(1L), result);
    }
}