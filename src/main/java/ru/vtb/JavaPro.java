package ru.vtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vtb.services.TestRunnerService;
import ru.vtb.services.TestRunnerServiceImpl;
import ru.vtb.test.MyTest;

//@SpringBootApplication
public class JavaPro {


  public static void main(String[] args) {
 //   SpringApplication.run(JavaPro.class, args);
    TestRunnerService testRunnerService = new TestRunnerServiceImpl();
    testRunnerService.runTest(MyTest.class);
  }

}
