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
class GetEmployeeByIdTest {
    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private GetEmployeeById getEmployeeById;

    @Test
    public void shouldGetEmployeeById() {
        long employeeId = 123L;
        Employee mockEmployee = new Employee(employeeId, "John Doe", 50000, 30, "profile.jpg");
        when(employeeGateway.get(employeeId)).thenReturn(Optional.of(mockEmployee));
        Optional<Employee> result = getEmployeeById.apply(employeeId);
        verify(employeeGateway, times(1)).get(employeeId);
        assertEquals(Optional.of(mockEmployee), result);
    }
}