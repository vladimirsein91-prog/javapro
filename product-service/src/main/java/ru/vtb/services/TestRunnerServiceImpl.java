package ru.vtb.services;


import lombok.extern.log4j.Log4j2;
import ru.vtb.annotation.*;
import ru.vtb.enums.TestResult;
import ru.vtb.enums.TestType;
import ru.vtb.exception.BadTestClassError;
import ru.vtb.exception.TestAssertionError;
import ru.vtb.pojo.TestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class TestRunnerServiceImpl implements TestRunnerService {

    @Override
    public Map<TestResult, TestMethod> runTest(Class clz) {
        var result = new HashMap<TestResult, TestMethod>();
        var testMethods = new ArrayList<TestMethod>();
        String step = "";
        try {
            step = "Создание экземпляра класса";
            // Пробуем создать обьект
            if (clz.getDeclaredConstructors().length > 1) {
                throw new BadTestClassError(String.format("более одного конструктора в классе %s недопустимо", clz.getName()));
            }
            var obj = clz.getDeclaredConstructors()[0].newInstance();
            // Получем список служебных
            var staff = getStaffMethod(clz);
            log.info(step);
            staff.stream().filter(method -> method.getTestType() == TestType.BEFORE_SUITE)
                    .peek(name -> log.info("run " + name))
                    .forEach(staffRun -> testMethods.add(runMethod(obj, staffRun)));
            step = "run single tests...";
            log.info(step);
            getAllreadyMethodTest(clz).stream()
                    .filter(method -> method.getTestType() == TestType.SINGLE)
                    .sorted(Comparator.comparing(TestMethod::getOrder, Comparator.reverseOrder())
                            .thenComparing(TestMethod::getTestName))
                    .forEach(single -> {
                        staff.stream().filter(method -> method.getTestType() == TestType.BEFORE_EACH)
                                .peek(name -> log.info("run " + name.getTestName()))
                                .forEach(staffRun -> testMethods.add(runMethod(obj, staffRun)));
                        //  log.info("running " + single.getTestName());
                        testMethods.add(runMethod(obj, single));
                        log.info("run " + single.getTestName() + " " + single.getTestResult());
                        staff.stream().filter(method -> method.getTestType() == TestType.AFTER_EACH)
                                .peek(name -> log.info("run " + name.getTestName()))
                                .forEach(staffRun -> testMethods.add(runMethod(obj, staffRun)));
                    });
            log.info(step);
            staff.stream().filter(method -> method.getTestType() == TestType.AFTER_SUITE)
                    .peek(name -> log.info("run " + name))
                    .forEach(staffRun -> testMethods.add(runMethod(obj, staffRun)));
            log.info("test results...");
            testMethods.stream()
                    .filter(m -> m.getTestType() == TestType.SINGLE)
                    .forEach(method -> {
                log.info(method);
                result.put(method.getTestResult(), method);
            });
            return result;
        } catch (BadTestClassError e) {
            log.error("Ошибка вызова класса " + clz.getName() + " шаг " + step + e.getMyMessage());
            return null;
        } catch (Exception e) {
            log.error("Ошибка вызова класса " + clz.getName() + " шаг " + step + e.getMessage());
            return null;
        }
    }

    private TestMethod runMethod(Object obj, TestMethod method) {
        if (method.getMethod().isVarArgs()) {
            throw new BadTestClassError(String.format("метод %s содержит параметры, что не допустимо",
                    method.getTestName()));
        }
        try {
            if (method.getIsSkipped()) {
                method.setTestResult(TestResult.SKIPPED);
                return method;
            }
            method.getMethod().invoke(obj);
            method.setTestResult(TestResult.SUCCESS);
            return method;
        } catch (Exception e) {
            if (e.getCause() instanceof TestAssertionError) {
                method.setTestResult(TestResult.FAILED);
                method.setMessageError(e.getMessage());
                return method;
            }
            method.setTestResult(TestResult.ERROR);
            method.setMessageError(e.getMessage());
            return method;
        }
    }

    /**
     * Получение всех методов для запуска теста
     *
     * @param clz
     * @return Список методов тестов
     */
    private List<TestMethod> getAllreadyMethodTest(Class clz) {
        return Arrays.stream(clz.getMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(method -> new TestMethod(
                        TestType.SINGLE,
                        method,
                        !method.getAnnotation(Test.class).name().equals("") ? method.getAnnotation(Test.class).name() :
                                method.getName(),
                        // если ордер не задан берем то что указан на @TEST ( в задании не совсем ясно)
                        method.isAnnotationPresent(Order.class) ? method.getAnnotation(Order.class).value() :
                                method.getAnnotation(Test.class).order(),
                        method.isAnnotationPresent(Disabled.class)))
                .collect(Collectors.toList());
    }

    /**
     * Получение служебных методов
     *
     * @param clz
     * @return Список служеюных методов
     */
    private List<TestMethod> getStaffMethod(Class clz) {
        var testMethodList = new ArrayList<TestMethod>();
        if (clz.getMethods().length == 0) {
            return testMethodList;
        }
        for (Method method : clz.getMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                //BeforeSuite
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new BadTestClassError(String.format("метод %s помечен аннатацией BeforeSuite, но не статичен", method.getName()));
                } else {
                    testMethodList.add(new TestMethod(TestType.BEFORE_SUITE, method, method.getName()));
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                //AfterSuite
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new BadTestClassError(String.format("метод %s помечен аннатацией AfterSuite, но не статичен", method.getName()));
                } else {
                    testMethodList.add(new TestMethod(TestType.AFTER_SUITE, method, method.getName()));
                }
            }
            if (method.isAnnotationPresent(BeforeEach.class)) {
                //BeforeEach
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new BadTestClassError(String.format("метод %s помечен аннатацией BeforeEach, но статичен", method.getName()));
                } else {
                    testMethodList.add(new TestMethod(TestType.BEFORE_EACH, method, method.getName()));
                }
            }
            if (method.isAnnotationPresent(AfterEach.class)) {
                //AfterEach
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new BadTestClassError(String.format("метод %s помечен аннатацией AfterEach, но статичен", method.getName()));
                } else {
                    testMethodList.add(new TestMethod(TestType.AFTER_EACH, method, method.getName()));
                }
            }
        }
        return testMethodList;
    }


}
