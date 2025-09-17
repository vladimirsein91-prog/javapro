package ru.vtb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableConfigurationProperties
public class JavaPro {

  public static void main(String[] args) {
    SpringApplication.run(JavaPro.class, args);
  }

}
