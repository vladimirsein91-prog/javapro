package ru.vtb.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestType {
    AFTER_EACH("AfterEach"),
    BEFORE_EACH("BeforeEach"),
    BEFORE_SUITE("BeforeSuite"),
    AFTER_SUITE("AfterSuite"),
    SINGLE("test");

    private String name;

}
