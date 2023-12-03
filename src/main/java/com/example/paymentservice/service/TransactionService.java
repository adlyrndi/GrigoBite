package com.example.paymentservice.service;
import com.example.paymentservice.dto.OrderPaymentRequest;
import com.example.paymentservice.model.Transaction;
import java.util.List;

public interface TransactionService {
    Transaction create(OrderPaymentRequest orderPaymentRequest, Long orderId); //ini buat payment
    List<Transaction> getTransactionHistory(Long orderId); //ini buat history

}
