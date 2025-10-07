package ru.vtb.controller;

import ru.vtb.pojo.PayRequest;
import ru.vtb.pojo.PayResult;
import ru.vtb.pojo.Product;

import java.util.List;

public interface PayServiceController {
    PayResult pay(PayRequest body);

    List<Product> getProducts(Integer userId);
}
