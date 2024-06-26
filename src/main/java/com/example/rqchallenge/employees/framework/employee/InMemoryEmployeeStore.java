package com.example.rqchallenge.employees.framework.employee;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InMemoryEmployeeStore implements Supplier<Map<Long, Employee>> {
    private final Map<Long, Employee> employeesById;

    public InMemoryEmployeeStore() {
        this.employeesById = loadEmployees();
    }

    private Map<Long, Employee> loadEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Employee>> typeReference = new TypeReference<>() {
        };
        try {
            ClassPathResource resource = new ClassPathResource("employee.json");
            List<Employee> employees = mapper.readValue(resource.getInputStream(), typeReference);
            return employees.stream().collect(Collectors.toMap(Employee::getId, e -> e, (e1, e2) -> e1));
        } catch (IOException e) {
            log.error("Error while loading json from file.", e);
            return new HashMap<>();
        }
    }

    @Override
    public Map<Long, Employee> get() {
        return employeesById;
    }
}
