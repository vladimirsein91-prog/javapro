package ru.vtb.services;

import ru.vtb.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductByUser(Integer userId);

    Product getProductById(Integer productId);


}
