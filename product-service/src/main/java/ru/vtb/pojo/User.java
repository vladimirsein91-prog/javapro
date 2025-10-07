package ru.vtb.pojo;

import lombok.Data;
import ru.vtb.entity.Department;

@Data
public class User {
    private Long id;
    private String userName;
    private Department department;
}
