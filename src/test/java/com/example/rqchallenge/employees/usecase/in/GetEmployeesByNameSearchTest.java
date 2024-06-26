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
class GetEmployeesByNameSearchTest {
    @Mock
    private GetAllEmployees getAllEmployees;

    @InjectMocks
    private GetEmployeesByNameSearch getEmployeesByNameSearch;

    @Test
    public void shouldReturnEmployeeWithMatchingName() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png")
        );
        when(getAllEmployees.get()).thenReturn(mockEmployees);
        List<Employee> result = getEmployeesByNameSearch.apply("Doe");
        verify(getAllEmployees, times(1)).get();
        assertEquals(1, result.size()); // Assuming only one employee matches "Doe"
        assertEquals("John Doe", result.get(0).getEmployeeName());
    }

    @Test
    public void shouldReturnEmptyListIfNothingMatches() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png")
        );
        when(getAllEmployees.get()).thenReturn(mockEmployees);
        List<Employee> result = getEmployeesByNameSearch.apply("Johnson");
        verify(getAllEmployees, times(1)).get();
        assertEquals(0, result.size());
    }
}