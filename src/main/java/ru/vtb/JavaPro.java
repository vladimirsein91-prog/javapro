package ru.vtb;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vtb.concurrency.MyThreadPool;
import ru.vtb.services.TestRunnerService;
import ru.vtb.services.TestRunnerServiceImpl;
import ru.vtb.test.MyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//@SpringBootApplication
@Slf4j
public class JavaPro {

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableConfigurationProperties
public class JavaPro {

    public static void main(String[] args) {
        //   SpringApplication.run(JavaPro.class, args);
        //TestRunnerService testRunnerService = new TestRunnerServiceImpl();
        // testRunnerService.runTest(MyTest.class);
        var pool = new MyThreadPool(6);
        pool.execute(() -> run(1000L, "запустили задачу 1"));
        pool.execute(() -> run(2000L, "запустили задачу 2"));
        pool.execute(() -> run(3000L, "запустили задачу 3"));
        pool.execute(() -> run(4000L, "запустили задачу 4"));
        pool.execute(() -> run(2000L, "запустили задачу 5"));
       // pool.awaitTermination();
        pool.execute(() -> run(10000L, "запустили задачу 6"));
        //pool.shutdown();
         pool.awaitTermination();
    }

    @SneakyThrows
    private static void run(Long time, String str) {
        log.info(str);
        Thread.sleep(time);
    }
}
