package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.entity.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class GetTop10HighestEarningEmployeeNames implements Supplier<List<String>> {
    private final GetTopNHighestEarningEmployee getTopNHighestEarningEmployee;

    @Autowired
    public GetTop10HighestEarningEmployeeNames(GetTopNHighestEarningEmployee getTopNHighestEarningEmployee) {
        this.getTopNHighestEarningEmployee = getTopNHighestEarningEmployee;
    }

    @Override
    public List<String> get() {
        return getTopNHighestEarningEmployee.apply(10).stream()
                .map(Employee::getEmployeeName)
                .collect(Collectors.toList());
    }
}
