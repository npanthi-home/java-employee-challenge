package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetTopNHighestEarningEmployeeTest {
    @Mock
    private GetAllEmployees getAllEmployees;

    @InjectMocks
    private GetTopNHighestEarningEmployee getTopNHighestEarningEmployee;

    @Test
    public void shouldGetTopNEmployeesForMoreThanNEmployees() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png"),
                new Employee(3L, "Michael Johnson", 55000, 32, "photo.jpg"),
                new Employee(4L, "Emily Brown", 58000, 27, "pic.png"),
                new Employee(5L, "David Lee", 62000, 29, "image.jpg"),
                new Employee(6L, "Sophia Garcia", 53000, 31, "avatar.jpg"),
                new Employee(7L, "Daniel Martinez", 59000, 26, "profile.png"),
                new Employee(8L, "Isabella Rodriguez", 61000, 33, "photo.png"),
                new Employee(9L, "Matthew Hernandez", 54000, 34, "pic.jpg"),
                new Employee(10L, "Emma Wilson", 57000, 25, "image.png"),
                new Employee(11L, "Olivia King", 63000, 27, "avatar.png")
        );

        when(getAllEmployees.get()).thenReturn(mockEmployees);
        Collection<Employee> result = getTopNHighestEarningEmployee.apply(5);
        verify(getAllEmployees, times(1)).get();
        assertEquals(5, result.size());
        List<String> expectedNames = Arrays.asList("David Lee", "Olivia King", "Isabella Rodriguez", "Jane Smith", "Daniel Martinez");
        List<String> actualNames = result.stream().map(Employee::getEmployeeName).collect(Collectors.toList());
        assertEquals(expectedNames.size(), result.size());
        assertTrue(actualNames.containsAll(expectedNames));
    }

    @Test
    public void shouldGetAllEmployeeNamesForLessThanNEmployees() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000, 30, "profile.jpg"),
                new Employee(2L, "Jane Smith", 60000, 28, "avatar.png"),
                new Employee(3L, "Michael Johnson", 55000, 32, "photo.jpg"),
                new Employee(4L, "Emily Brown", 58000, 27, "pic.png")
        );

        when(getAllEmployees.get()).thenReturn(mockEmployees);
        Collection<Employee> result = getTopNHighestEarningEmployee.apply(5);
        verify(getAllEmployees, times(1)).get();
        assertEquals(4, result.size());
        List<String> expectedNames = Arrays.asList("John Doe", "Jane Smith", "Michael Johnson", "Emily Brown");
        List<String> actualNames = result.stream().map(Employee::getEmployeeName).collect(Collectors.toList());
        assertEquals(expectedNames.size(), result.size());
        assertTrue(actualNames.containsAll(expectedNames));
    }

    @Test
    public void shouldGetEmptyListFor0N() {
        Collection<Employee> result = getTopNHighestEarningEmployee.apply(0);
        verify(getAllEmployees, times(0)).get();
        assertEquals(0, result.size());
    }
}