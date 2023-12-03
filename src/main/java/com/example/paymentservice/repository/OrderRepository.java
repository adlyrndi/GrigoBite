package com.example.paymentservice.repository;

import com.example.paymentservice.model.Order;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    @NonNull
    List<Order> findAllbyOrderId(@NonNull Integer orderId);
}
