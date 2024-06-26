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
class GetTop10HighestEarningEmployeeNamesTest {
    @Mock
    private GetTopNHighestEarningEmployee getTopNHighestEarningEmployee;

    @InjectMocks
    private GetTop10HighestEarningEmployeeNames getTop10HighestEarningEmployeeNames;

    @Test
    public void shouldEmployeeNamesTopEarningEmployees() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png")
        );

        when(getTopNHighestEarningEmployee.apply(10)).thenReturn(mockEmployees);
        List<String> result = getTop10HighestEarningEmployeeNames.get();
        verify(getTopNHighestEarningEmployee, times(1)).apply(10);
        assertEquals(Arrays.asList("John Doe", "Jane Smith"), result);
    }
}