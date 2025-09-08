package ru.vtb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @Column(name = "id", columnDefinition = "NUMERIC", length = 20)
    private Long id;

    @Column(name="user_name")
    private String userName;

}
