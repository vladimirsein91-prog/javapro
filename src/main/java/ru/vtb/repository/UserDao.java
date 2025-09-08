package ru.vtb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.entity.UserEntity;

import java.util.List;

@Repository
    public interface UserDao extends JpaRepository<UserEntity, Long> {
         List<UserEntity> findAll();

         void removeById(Long id);
    }
