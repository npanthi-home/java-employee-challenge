package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class UpdateEmployee implements Function<Employee, Optional<Long>> {
    private final EmployeeGateway employeeGateway;

    @Autowired
    public UpdateEmployee(@Qualifier(EmployeeGateway.BEAN_NAME) EmployeeGateway employeeGateway) {
        this.employeeGateway = employeeGateway;
    }

    @Override
    public Optional<Long> apply(Employee employee) {
        return employeeGateway.update(employee);
    }
}
