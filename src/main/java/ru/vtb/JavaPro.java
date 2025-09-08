package ru.vtb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.vtb.pojo.User;
import ru.vtb.services.UserService;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableConfigurationProperties
public class JavaPro {

  public static void main(String[] args) {
    ApplicationContext ctx =  SpringApplication.run(JavaPro.class, args); 
    UserService mr = ctx.getBean(UserService.class);
    User usrn = new User();
    usrn.setId(1L);
    usrn.setUserName("test1");
    mr.saveUser(usrn);
    User usrn2 = new User();
    usrn2.setId(2L);
    usrn2.setUserName("test2");
    mr.saveUser(usrn2);
    System.out.println(mr.getUser(1L));
    System.out.println(mr.getAllUsers());
    mr.removeUser(1L);
    mr.removeUser(2L);

  }

}
