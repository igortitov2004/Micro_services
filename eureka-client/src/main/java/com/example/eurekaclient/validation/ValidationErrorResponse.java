package com.example.eurekaclient.validation;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    public List<Violation> getViolations() {
        return violations;
    }

    private final List<Violation> violations;
}
