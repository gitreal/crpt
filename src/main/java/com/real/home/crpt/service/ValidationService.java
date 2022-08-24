package com.real.home.crpt.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private Validator validator;

    @PostConstruct
    private void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> List<String> validate(T validateObject) {
        final Set<ConstraintViolation<T>> violations = validator.validate(validateObject);
        return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

}
