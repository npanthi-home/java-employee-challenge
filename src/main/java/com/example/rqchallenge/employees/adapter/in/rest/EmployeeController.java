package com.example.rqchallenge.employees.adapter.in.rest;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.framework.employee.MapToEmployee;
import com.example.rqchallenge.employees.usecase.in.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController implements IEmployeeController {
    private final GetAllEmployees getAllEmployees;
    private final GetEmployeesByNameSearch getEmployeesByNameSearch;
    private final GetEmployeeById getEmployeeById;
    private final GetHighestSalary getHighestSalary;
    private final GetTop10HighestEarningEmployeeNames getTop10HighestEarningEmployeeNames;
    private final MapToEmployee mapToEmployee;
    private final CreateEmployee createEmployee;
    private final DeleteEmployee deleteEmployee;

    @Autowired
    public EmployeeController(
            GetAllEmployees getAllEmployees,
            GetEmployeesByNameSearch getEmployeesByNameSearch,
            GetEmployeeById getEmployeeById,
            GetHighestSalary getHighestSalary,
            GetTop10HighestEarningEmployeeNames getTop10HighestEarningEmployeeNames,
            MapToEmployee mapToEmployee,
            CreateEmployee createEmployee,
            DeleteEmployee deleteEmployee
    ) {
        this.getAllEmployees = getAllEmployees;
        this.getEmployeesByNameSearch = getEmployeesByNameSearch;
        this.getEmployeeById = getEmployeeById;
        this.getHighestSalary = getHighestSalary;
        this.getTop10HighestEarningEmployeeNames = getTop10HighestEarningEmployeeNames;
        this.mapToEmployee = mapToEmployee;
        this.createEmployee = createEmployee;
        this.deleteEmployee = deleteEmployee;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        return respond(() -> ResponseEntity.ok(getAllEmployees.get()));
    }

    @GetMapping("/search/{searchString}")
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        return respond(() -> ResponseEntity.ok(getEmployeesByNameSearch.apply(searchString)));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        return respond(() ->
                getEmployeeById.apply(Long.valueOf(id))
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.ok().build())
        );
    }

    @GetMapping("/highestSalary")
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return respond(() -> ResponseEntity.ok(getHighestSalary.get()));
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return respond(() -> ResponseEntity.ok(getTop10HighestEarningEmployeeNames.get()));
    }

    @PostMapping()
    @Override
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {
        return respond(() ->
                createEmployee.apply(mapToEmployee.apply(employeeInput))
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.ok().build())
        );
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        return respond(() ->
                deleteEmployee.apply(Long.valueOf(id))
                        .map((i) -> ResponseEntity.ok("success"))
                        .orElseGet(() -> ResponseEntity.ok("failure"))
        );
    }

    private <T> ResponseEntity<T> respond(Supplier<ResponseEntity<T>> execution) {
        try {
            return execution.get();
        } catch (Exception e) {
            log.error("Could not complete request.", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
