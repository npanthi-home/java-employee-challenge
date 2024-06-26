package com.example.rqchallenge.employees.usecase.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Supplier;

@Component
public class GetHighestSalary implements Supplier<Integer> {
    private final GetTopNHighestEarningEmployee getTopNHighestEarningEmployee;

    @Autowired
    public GetHighestSalary(GetTopNHighestEarningEmployee getTopNHighestEarningEmployee) {
        this.getTopNHighestEarningEmployee = getTopNHighestEarningEmployee;
    }

    @Override
    public Integer get() {
        return new ArrayList<>(getTopNHighestEarningEmployee.apply(1))
                .get(0)
                .getEmployeeSalary();
    }
}
