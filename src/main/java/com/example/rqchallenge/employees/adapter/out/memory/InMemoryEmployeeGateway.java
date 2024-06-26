package com.example.rqchallenge.employees.adapter.out.memory;

import com.example.rqchallenge.employees.entity.model.Employee;
import com.example.rqchallenge.employees.entity.service.GenerateId;
import com.example.rqchallenge.employees.framework.employee.InMemoryEmployeeStore;
import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Qualifier(EmployeeGateway.BEAN_NAME)
public class InMemoryEmployeeGateway implements EmployeeGateway {
    private final Map<Long, Employee> store;
    private final GenerateId generateId;

    @Autowired
    public InMemoryEmployeeGateway(InMemoryEmployeeStore employeeStore, GenerateId generateId) {
        this.store = employeeStore.get();
        this.generateId = generateId;
    }

    @Override
    public Optional<Employee> get(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Employee> getAll() {
        return new LinkedList<>(store.values());
    }

    @Override
    public Optional<Employee> create(Employee employee) {
        Long id = generateId.get();
        employee.setId(id);
        this.store.put(id, employee);
        return Optional.of(employee);
    }

    @Override
    public Optional<Long> delete(long id) {
        return this.get(id).map(e -> {
            store.remove(e.getId());
            return e.getId();
        });
    }

    @Override
    public Optional<Long> update(Employee employee) {
        return this.get(employee.getId()).map(e -> {
            store.put(e.getId(), employee);
            return e.getId();
        });
    }
}
