package ru.vtb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
    public interface UserDao extends JpaRepository<UserEntity, Long> {

         @Query("select e from UserEntity e join fetch e.department where e.id = :id")
         Optional<UserEntity> findById(Integer id);
         @Query("select e from UserEntity e join fetch e.department")
         List<UserEntity> findAll();

    @Query("select e from UserEntity e join fetch e.department where e.department.id =:id")
    List<UserEntity> findByDepartment(Integer id);
         void removeById(Long id);
    }
