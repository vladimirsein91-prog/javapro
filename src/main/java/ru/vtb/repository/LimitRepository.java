package ru.vtb.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.vtb.entity.LimitEntity;

import java.time.LocalDate;
import java.util.List;

public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    @Query("select lo from LimitEntity lo left join fetch lo.operations where lo.userId = :userId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<LimitEntity> getLimitEntityByUserId(Long userId);

    @Query("select lo from LimitEntity lo left join fetch lo.operations where lo.userId = :userId and lo.operDate= :operDay")
    List<LimitEntity> getLimitEntityByUserIdAndOperDay(Long userId, LocalDate operDay);

}
