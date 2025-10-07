package ru.vtb.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vtb.mappers.MapperProduct;
import ru.vtb.pojo.Product;
import ru.vtb.repository.ProductDao;
import ru.vtb.services.ProductService;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final MapperProduct mapperProduct;

    @Override
    public List<Product> getProductByUser(Integer userId) {
        return mapperProduct.map(productDao.findByUserId(userId));
    }

    @Override
    public Product getProductById(Integer productId) {
        return mapperProduct.map(productDao.getById(productId));
    }
}
