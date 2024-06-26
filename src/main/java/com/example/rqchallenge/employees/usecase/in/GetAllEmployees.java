package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/*
  TODO: Ideally pagination should be implemented on such a request. But since the task is to return all employees, I am doing that.
 */
@Component
public class GetAllEmployees implements Supplier<List<Employee>> {
    private final EmployeeGateway employeeGateway;

    @Autowired
    public GetAllEmployees(@Qualifier(EmployeeGateway.BEAN_NAME) EmployeeGateway employeeGateway) {
        this.employeeGateway = employeeGateway;
    }
    @Override
    public List<Employee> get() {
        return employeeGateway.getAll();
    }
}
