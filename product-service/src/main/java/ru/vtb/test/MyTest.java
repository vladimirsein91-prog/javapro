package ru.vtb.test;

import lombok.extern.log4j.Log4j2;
import ru.vtb.annotation.*;
import ru.vtb.exception.TestAssertionError;

@Log4j2
public class MyTest {

    @Test(order = 1)
    @Order(7)
    public void test0(){
        log.info("test0 учпех");
    }

    @Test()
    public void test1(){
        log.info("test1 успех");
    }
    @Test()
    @Order(8)
    public void test1Fail(){
        log.info("test1 не успех");
        throw new TestAssertionError();
    }

    @Test()
    @Disabled
    public void test2(){
        log.info("test2 успех");
    }
    @BeforeSuite
    public static void beforeSiute(){
        log.info("BeforeSuite успех");
    }
    @AfterSuite
    public static void afterSuite(){
        log.info("AfterSuite успех");
    }
    @BeforeEach
    public  void beforeEach(){
        log.info("BeforeEach успех");
    }

    @AfterEach
    public void afterEach(){
        log.info("AfterEach успех");
    }



}
