package com.example.sap.service;

import org.springframework.stereotype.Service;

@Service
public class OrderClient {

    private final OrderService service;

    public OrderClient() {
        service = new OrderService();
    }

    public CreateOrderResponse createOrder(String orderId, double amount) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrderID(orderId);
        request.setAmount(amount);

        CreateOrderResponse response = service.getOrderPort().createOrder(request);

        // Adicionando campo "message" manualmente para MVP
        // (não existe no WSDL, apenas para testes)
        // Criamos uma subclasse rápida de wrapper
        CreateOrderResponseWrapper wrapper = new CreateOrderResponseWrapper(response);
        wrapper.setMessage("Order created successfully");

        return wrapper;
    }

    // Wrapper interno para adicionar message
    public static class CreateOrderResponseWrapper extends CreateOrderResponse {
        private String message;

        public CreateOrderResponseWrapper(CreateOrderResponse base) {
            this.setOrderID(base.getOrderID());
            this.setAmount(base.getAmount());
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}