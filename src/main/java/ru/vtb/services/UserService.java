package ru.vtb.services;

import org.springframework.stereotype.Service;
import ru.vtb.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    Long saveUser(User user);

    void removeUser(Long id);
}
