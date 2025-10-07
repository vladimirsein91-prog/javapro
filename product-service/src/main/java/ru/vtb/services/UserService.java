package ru.vtb.services;

import ru.vtb.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    List<User> getAllusersByDepartament(Long id);
    Long saveUser(User user);

    void removeUser(Long id);
}
