package ru.vtb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@Data
public class Department {
    @Id
    Integer id;
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @ToString.Exclude
    List<UserEntity> userEntityList;
}
