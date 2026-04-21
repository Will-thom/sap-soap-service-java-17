package com.example.sap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sap.service.OrderClient;

@RestController
public class TestController {

    private final OrderClient orderClient;

    public TestController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @GetMapping("/test")
    public CreateOrderResponse test() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrderId("1001");
        request.setAmount(200.0);

        return orderClient.createOrder(request);
    }
}