package com.example.rqchallenge.employees.usecase.out;

import com.example.rqchallenge.employees.entity.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeGateway {
    String BEAN_NAME = "com.example.rqchallenge.employees.usecase.out.EmployeeGateway";

    Optional<Employee> get(long id);
    List<Employee> getAll();
    Optional<Employee> create(Employee employee);
    Optional<Long> delete(long id);
    Optional<Long> update(Employee employee);
}
