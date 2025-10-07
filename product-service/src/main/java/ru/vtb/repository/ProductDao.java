package ru.vtb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vtb.entity.ProductEntity;

import java.util.List;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

    @Query("select e from ProductEntity e join fetch e.user where e.user.id =:userId")
    List<ProductEntity> findByUserId(Integer userId);

    @Query("select e from ProductEntity e join fetch e.user where e.id =:id")
     ProductEntity getById(Integer id);
}
