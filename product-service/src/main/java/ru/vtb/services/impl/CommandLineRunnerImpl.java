package ru.vtb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.vtb.services.UserService;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Override
    public void run(String... args) throws Exception {
//        User usrn = new User();
//        Department dep = new Department();
//        dep.setId(1);
//        dep.setName("топы");
//        usrn.setId(1L);
//        usrn.setUserName("test1");
//        usrn.setDepartment(dep);
//        userService.saveUser(usrn);
//        User usrn2 = new User();
//        usrn2.setId(2L);
//        usrn2.setUserName("test2");
//        usrn2.setDepartment(dep);
//        userService.saveUser(usrn2);
        System.out.println(userService.getUser(1L)); // Взять один
        System.out.println(userService.getAllUsers()); // Взять всех юзеров
        System.out.println(userService.getAllusersByDepartament(1L)); // взять всех юзеров в 1 департаменте
//        userService.removeUser(1L);
//        userService.removeUser(2L);
    }
}
