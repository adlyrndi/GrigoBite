package com.grigoBiteUI.controller;

import com.grigoBiteUI.model.Payment.Transaction;
import com.grigoBiteUI.service.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public ResponseEntity<Void> pay(@PathVariable Long pesananId, @PathVariable Long pembeliId, @PathVariable Long penjualId){
        boolean paymentSuccess = paymentService.pay(pesananId, pembeliId, penjualId);
        if(paymentSuccess){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/top-up")
    public ResponseEntity<Void> topup(@PathVariable double amount, @PathVariable Long pembeliId) {
        boolean topupSuccessful = paymentService.topup(pembeliId, amount);

        if (topupSuccessful) {
            return ResponseEntity.ok().build(); // Top-up berhasil, HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/view-wallet")
    public ResponseEntity<List<Transaction>> viewWallet(@PathVariable Long pembeliId) {
        var transactions = paymentService.viewWallet(pembeliId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/view-earnings")
    public ResponseEntity<List<Transaction>> viewEarnings(@PathVariable Long penjualId) {
        var transactions = paymentService.viewEarnings(penjualId);
        return ResponseEntity.ok(transactions);
    }



}
