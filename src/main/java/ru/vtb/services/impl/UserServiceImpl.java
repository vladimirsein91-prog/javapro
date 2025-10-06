package ru.vtb.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.mappers.MapperUser;
import ru.vtb.pojo.User;
import ru.vtb.repository.UserDao;
import ru.vtb.services.UserService;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final MapperUser mapper;

    @Override
    public List<User> getAllUsers() {
        return mapper.map(userDao.findAll());
    }

    @Override
    public User getUser(Long id) {
        return mapper.map(userDao.findById(id.intValue()).get());
    }

    @Override
    public List<User> getAllusersByDepartament(Long id) {
        return mapper.map(userDao.findByDepartment(id.intValue()));
    }

    @Override
    public Long saveUser(User user) {
        userDao.save(mapper.map(user));
        return user.getId();
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDao.removeById(id);
    }
}
