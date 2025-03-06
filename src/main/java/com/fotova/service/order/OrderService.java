package com.fotova.service.order;

import com.fotova.dto.stripe.StripeProductRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    public StripeProductRequest setStripeProductRequestName(StripeProductRequest stripeProductRequest) {
        stripeProductRequest.setName(UUID.randomUUID().toString());
        return stripeProductRequest;
    }
}
