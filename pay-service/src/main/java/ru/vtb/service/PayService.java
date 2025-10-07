package ru.vtb.service;

import ru.vtb.pojo.PayRequest;
import ru.vtb.pojo.PayResult;
import ru.vtb.pojo.Product;

import java.util.List;

public interface PayService {
    // получить список продуктов
    List<Product> getProductByUser(Integer userId);
    // провести платеж
    PayResult makePay(PayRequest body);
}
