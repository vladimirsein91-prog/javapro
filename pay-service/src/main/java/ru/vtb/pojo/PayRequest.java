package ru.vtb.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayRequest {
    String userId;
    String productName;
    BigDecimal amount;
}
