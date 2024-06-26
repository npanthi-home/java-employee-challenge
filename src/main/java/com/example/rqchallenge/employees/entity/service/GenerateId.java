package com.example.rqchallenge.employees.entity.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.function.Supplier;

@Component
public class GenerateId implements Supplier<Long> {
    private final SecureRandom secureRandom = new SecureRandom();
    @Override
    public Long get() {
        return System.currentTimeMillis() + secureRandom.nextInt();
    }
}
