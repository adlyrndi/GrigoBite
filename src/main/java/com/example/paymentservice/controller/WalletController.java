package com.example.paymentservice.controller;


import com.example.paymentservice.model.Transaction;
import com.example.paymentservice.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.paymentservice.dto.TopUpRequest;
import com.example.paymentservice.dto.WithdrawalRequest;
import com.example.paymentservice.dto.OrderPaymentRequest;
// WalletController.java
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/top-up")
    public ResponseEntity<String> topUpWallet(@RequestBody TopUpRequest topUpRequest) {
        // Implement logic to update the user's wallet balance
        // Save the transaction record in the database
        return ResponseEntity.ok("Top-up successful");
    }

    @GetMapping("/transactions-history")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long orderId) {
        // Implement logic to fetch the user's transaction history
        return ResponseEntity.ok(transactionService.getTransactionHistory(orderId));
    }
    @PostMapping("/pay-order/{transaction-id}")
    public ResponseEntity<String> payOrder(@RequestBody OrderPaymentRequest orderPaymentRequest, @PathVariable("transaction-id") Integer parameter) {
        // Implement logic to deduct the order amount from the user's wallet
        // Save the transaction record in the database
        // Send a confirmation email to the buyer
        return ResponseEntity.ok("Order payment successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawalRequest withdrawalRequest) {
        // Implement logic to process the withdrawal for the seller
        // Save the withdrawal record in the database
        return ResponseEntity.ok("Withdrawal request submitted");
    }




}
