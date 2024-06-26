package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetHighestSalaryTest {
    @Mock
    private GetTopNHighestEarningEmployee getTopNHighestEarningEmployee;

    @InjectMocks
    private GetHighestSalary getHighestSalary;

    @Test
    public void shouldGetHighestSalary() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png")
        );

        when(getTopNHighestEarningEmployee.apply(1)).thenReturn(mockEmployees);
        Integer result = getHighestSalary.get();
        verify(getTopNHighestEarningEmployee, times(1)).apply(1);
        assertEquals(60000, result);
    }
}