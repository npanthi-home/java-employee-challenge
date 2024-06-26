package com.example.rqchallenge.employees.usecase.in;

import com.example.rqchallenge.employees.usecase.out.EmployeeGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeTest {
    @Mock
    private EmployeeGateway employeeGateway;

    @InjectMocks
    private DeleteEmployee deleteEmployee;

    @Test
    public void shouldDeleteAsset() {
        Long employeeId = 123L;
        when(employeeGateway.delete(employeeId)).thenReturn(Optional.of(employeeId));
        Optional<Long> result = deleteEmployee.apply(employeeId);
        verify(employeeGateway, times(1)).delete(employeeId);
        assertEquals(Optional.of(employeeId), result);
    }
}