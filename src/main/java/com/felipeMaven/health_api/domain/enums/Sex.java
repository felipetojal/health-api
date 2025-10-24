package com.felipeMaven.health_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private final String displayValue;

}
