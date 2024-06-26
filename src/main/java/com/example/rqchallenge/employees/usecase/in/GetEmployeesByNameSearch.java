package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GetEmployeesByNameSearch implements Function<String, List<Employee>> {
    private final GetAllEmployees getAllEmployees;

    @Autowired
    public GetEmployeesByNameSearch(GetAllEmployees getAllEmployees) {
        this.getAllEmployees = getAllEmployees;
    }

    @Override
    public List<Employee> apply(String searchString) {
        return getAllEmployees.get().stream()
                .filter(e -> e.getEmployeeName().contains(searchString))
                .collect(Collectors.toList());
    }
}
