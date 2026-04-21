package com.example.sap.endpoint;

import com.example.sap.service.OrderClient;
import com.example.sap.service.OrderClient.CreateOrderResponseWrapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/sap/wsdl";
    private final OrderClient orderClient;

    public OrderEndpoint(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        CreateOrderResponseWrapper response = orderClient.createOrder(
                request.getOrderID(),
                request.getAmount()
        );

        // Aqui já temos message no wrapper
        System.out.println("Message: " + response.getMessage());

        return response;
    }
}