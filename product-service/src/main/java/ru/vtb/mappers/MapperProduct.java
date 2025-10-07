package ru.vtb.mappers;

import org.mapstruct.ReportingPolicy;
import ru.vtb.entity.ProductEntity;
import ru.vtb.pojo.Product;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MapperProduct {
    Product map(ProductEntity product);
    List<Product> map(List<ProductEntity> product);
}
