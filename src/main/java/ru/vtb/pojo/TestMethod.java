package ru.vtb.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import ru.vtb.enums.TestResult;
import ru.vtb.enums.TestType;

import java.lang.reflect.Method;

@Getter
@Setter
@ToString
public class TestMethod {
    TestType testType;
    Method method;
    String testName;
    Integer order;
    Boolean isSkipped;
    TestResult testResult;
    String messageError;

    public TestMethod(TestType testType, Method method, String testName, Integer order, Boolean isSkipped) {
        this.testType = testType;
        this.method = method;
        this.testName = testName;
        this.order = order;
        this.isSkipped = isSkipped;
    }

    public TestMethod(TestType testType, Method method, String testName) {
        this.testType = testType;
        this.method = method;
        this.testName = testName;
        this.order = 0;
        this.isSkipped = false;
    }
}