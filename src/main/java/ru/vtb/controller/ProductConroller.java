package ru.vtb.controller;

import org.springframework.stereotype.Controller;
import ru.vtb.pojo.Product;

import java.util.List;

public interface ProductConroller {

  List<Product>  getAllProduct(Integer userId);

  Product getProduct(Integer productId);
}
