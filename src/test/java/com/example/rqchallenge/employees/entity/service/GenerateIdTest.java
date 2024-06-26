package com.example.rqchallenge.employees.entity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenerateIdTest {
    private final GenerateId generateId = new GenerateId();
    @Test
    public void shouldNotCreateDuplicateIds() {
        Long id1 = generateId.get();
        Long id2 = generateId.get();
        Assertions.assertNotEquals(id1, id2);
    }

}