package ru.vtb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vtb.enums.TypeOperation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "limits")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LimitEntity {
    @Id
    @SequenceGenerator(name = "limits_id_seq_gen",
            sequenceName = "limits_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "limits_id_seq_gen")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="oper_date")
    private LocalDate operDate;
    private BigDecimal amount;
    @Column(name="user_id")
    private Long userId;
    @OneToMany(mappedBy = "limit",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    List<OperationEntity> operations;


}
