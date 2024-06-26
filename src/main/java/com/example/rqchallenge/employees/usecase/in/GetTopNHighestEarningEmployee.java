package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class GetTopNHighestEarningEmployee implements Function<Integer, Collection<Employee>> {
    private final GetAllEmployees getAllEmployees;

    @Autowired
    public GetTopNHighestEarningEmployee(GetAllEmployees getAllEmployees) {
        this.getAllEmployees = getAllEmployees;
    }

    @Override
    public Collection<Employee> apply(Integer n) {
        PriorityQueue<Employee> top10HighestEarningEmployees = new PriorityQueue<>(Comparator.comparingInt(Employee::getEmployeeSalary));
        if (n == 0) {
            return top10HighestEarningEmployees;
        }

        List<Employee> employees = getAllEmployees.get();
        for (Employee employee : employees) {
            if (top10HighestEarningEmployees.size() == n && top10HighestEarningEmployees.peek().getEmployeeSalary() < employee.getEmployeeSalary()) {
                top10HighestEarningEmployees.poll();
            }
            if (top10HighestEarningEmployees.size() < n) {
                top10HighestEarningEmployees.offer(employee);
            }
        }
        return top10HighestEarningEmployees;
    }
}
