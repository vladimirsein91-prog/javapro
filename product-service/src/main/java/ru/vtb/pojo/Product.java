package ru.vtb.pojo;

import lombok.Data;
import ru.vtb.enums.TypeProduct;

import java.math.BigDecimal;

@Data
public class Product {
    String account_number;
    BigDecimal amount;
    TypeProduct typeProduct;
}
