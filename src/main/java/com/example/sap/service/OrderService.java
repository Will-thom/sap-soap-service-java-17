package com.example.sap.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        CreateOrderResponse response = new CreateOrderResponse();

        if (request.getAmount() > 1000) {
            response.setStatus("REJECTED");
            response.setSapOrderId("NONE");
        } else {
            response.setStatus("CREATED");
            response.setSapOrderId("SAP-" + UUID.randomUUID());
        }

        try {
            Thread.sleep(400); // simula latência SAP
        } catch (InterruptedException ignored) {}

        return response;
    }
}