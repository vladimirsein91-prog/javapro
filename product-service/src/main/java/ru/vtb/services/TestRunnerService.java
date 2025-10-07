package ru.vtb.services;

import ru.vtb.enums.TestResult;
import ru.vtb.pojo.TestMethod;

import java.util.Map;

public interface TestRunnerService {
    public Map<TestResult, TestMethod> runTest(Class clz);
}
