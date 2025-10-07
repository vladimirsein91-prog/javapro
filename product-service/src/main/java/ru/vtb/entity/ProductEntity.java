package ru.vtb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import ru.vtb.enums.TypeProduct;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    Integer id;
    String account_number;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    TypeProduct typeProduct;
    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
}
