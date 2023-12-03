package com.example.paymentservice.repository;

import com.example.paymentservice.model.Transaction;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository {
    @NonNull
    List<Transaction> findAllByTransactionId(@NonNull Integer transactionId);
}
