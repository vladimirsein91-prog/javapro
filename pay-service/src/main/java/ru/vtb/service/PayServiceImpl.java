package ru.vtb.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vtb.exception.RequestException;
import ru.vtb.pojo.PayRequest;
import ru.vtb.pojo.PayResult;
import ru.vtb.pojo.Product;

import java.util.Arrays;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    public List<Product> getProductByUser(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/user/";
        ResponseEntity<Product[]> response
                = restTemplate.getForEntity(fooResourceUrl + userId, Product[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) // ошибок нет
        {
            return Arrays.asList(response.getBody());
        } else {
            throw new RequestException("Ошибка вызова метода получения продуктов");
        }
    }

    public PayResult makePay(PayRequest body) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/user/";
        ResponseEntity<Product[]> response
                = restTemplate.getForEntity(fooResourceUrl + body.getUserId(), Product[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) // ошибок нет
        {
            var result = response.getBody();
            var products = Arrays.asList(result);
            if (products.stream().noneMatch(p -> p.getTypeProduct().equals(body.getProductName()))) {
                throw new RequestException("продукт не найден!");
            }
            var product = products.stream().filter(p -> p.getTypeProduct().equals(body.getProductName()))
                    .findFirst()
                    .orElseThrow(() -> new RequestException("продукт не найден!"));
            if (product.getAmount().compareTo(body.getAmount()) < 0) {
                throw new RequestException("денег нет");
            }
            return new PayResult("платеж выполнен", "0");
        } else {
            throw new RequestException("Ошибка вызова метода получения продуктов");
        }
    }
}
