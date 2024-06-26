package com.example.rqchallenge.employees.framework.employee;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class MapToEmployee implements Function<Map<String, Object>, Employee> {
    @Override
    public Employee apply(Map<String, Object> input) {
        Employee.EmployeeBuilder builder = Employee.builder();
        if (input.containsKey("id") && input.get("id") != null) {
            builder.id(Long.valueOf((Integer) input.get("id")));
        }
        if (input.containsKey("employee_name") && input.get("employee_name") != null) {
            builder.employeeName((String) input.get("employee_name"));
        }
        if (input.containsKey("employee_salary") && input.get("employee_salary") != null) {
            builder.employeeSalary((Integer) input.get("employee_salary"));
        }
        if (input.containsKey("employee_age") && input.get("employee_age") != null) {
            builder.employeeAge((Integer) input.get("employee_age"));
        }
        if (input.containsKey("profile_image") && input.get("profile_image") != null) {
            builder.profileImage((String) input.get("profile_image"));
        }
        return builder.build();
    }
}
