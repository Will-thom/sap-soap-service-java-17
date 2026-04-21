package com.example.sap.mock;

import org.springframework.stereotype.Component;

@Component
public class MockSoapEndpoint {

    public CreateOrderResponse mockResponse() {
        // Usando wrapper para message
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderID("MOCK123");
        response.setAmount(999.99);

        // Adicionando message via subclass wrapper para MVP
        CreateOrderResponseWrapper wrapper = new CreateOrderResponseWrapper(response);
        wrapper.setMessage("This is a mock order response");

        return wrapper;
    }

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