package com.example.paymentservice.service;

import com.example.paymentservice.dto.OrderPaymentRequest;
import com.example.paymentservice.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Override
    public Transaction create(OrderPaymentRequest orderPaymentRequest, Long orderId) {

        return null;
    }

    @Override
    public List<Transaction> getTransactionHistory(Long orderId) {
        return null;
    }

}
