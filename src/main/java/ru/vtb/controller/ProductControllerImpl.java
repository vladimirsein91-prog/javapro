package ru.vtb.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.pojo.Product;
import ru.vtb.services.ProductService;

import java.util.List;

@RestController("/product")
@AllArgsConstructor
public class ProductControllerImpl implements ProductConroller {

    private final ProductService productService;

    @Override
    @GetMapping(value = "/user/{userId}")
    public List<Product> getAllProduct(@PathVariable("userId") Integer userId) {
        return productService.getProductByUser(userId);
    }

    @Override
    @GetMapping(value = "/{productId}")
    public Product getProduct(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId);
    }
}
