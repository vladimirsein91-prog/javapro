package ru.vtb.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.vtb.enums.StatusEnum;
import ru.vtb.enums.TypeOperation;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "operation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OperationEntity {
    @Id
    private UUID id;
    private BigDecimal amount;
    @Column(name="type_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "limit_id", nullable = false)
    @ToString.Exclude
    private LimitEntity limit;

}
