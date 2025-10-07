package ru.vtb.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vtb.pojo.PayRequest;
import ru.vtb.pojo.PayResult;
import ru.vtb.pojo.Product;
import ru.vtb.service.PayService;

import java.util.List;

@RestController("/pay")
@AllArgsConstructor
public class PayServiceControllerImpl implements PayServiceController {
    private final PayService payService;
    @Override
    @PostMapping(value = "/make")
    public PayResult pay(@RequestBody PayRequest body) {
        return payService.makePay(body);
    }

    @Override
    @GetMapping(value = "/user/{userId}")
    public List<Product> getProducts(@PathVariable("userId") Integer userId) {
        return payService.getProductByUser(userId);
    }
}
