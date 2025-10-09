package ru.vtb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.entity.OperationEntity;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<OperationEntity, UUID> {
}
