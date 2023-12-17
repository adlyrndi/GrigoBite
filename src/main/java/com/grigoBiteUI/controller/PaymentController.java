package com.grigoBiteUI.controller;
import com.grigoBiteUI.dto.RequestTopUp;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.service.PesananService;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.Transaction;
import com.grigoBiteUI.dto.RequestPayment;
import com.grigoBiteUI.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final PesananService pesananService;

    @Autowired
    public PaymentController(PaymentService paymentService, PesananService pesananService) {
        this.paymentService = paymentService;
        this.pesananService = pesananService;

    }
    @GetMapping("")
    public String showCheckoutPage(Model model) {
        var userId = getCurrentUserId();
        List<Pesanan> pesanan = pesananService.getPesananIncompleteFromPembeli(userId);
        model.addAttribute("pesanan", pesanan);
//        model.addAttribute("saldo",((Pembeli)user).getSaldo());
        return "payment-page";
    }


    @GetMapping("/view-wallet")
    public ResponseEntity<Optional<Transaction>> viewWallet(@PathVariable Long pembeliId) {
        Optional<Transaction> transactions = paymentService.viewWallet(pembeliId);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/view-earnings")
    public ResponseEntity<Optional<Transaction>> viewEarnings(@PathVariable Long penjualId) {
        var transactions = paymentService.viewEarnings(penjualId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('canteen:crud')")
    public ResponseEntity<Canteen> createTransaction(RequestPayment requestPayment) {
        Transaction pay = paymentService.pay(requestPayment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/top-up")
    @PreAuthorize("hasAuthority('canteen:crud')")
    public ResponseEntity<Canteen> createTopUp(RequestTopUp requestTopUp) {
        Transaction pay = paymentService.topup(requestTopUp);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof User) {
            User userDetails = (User) authentication.getPrincipal();
            return userDetails.getId();// Gantilah dengan cara Anda mendapatkan ID pengguna dari token atau UserDetails.
        }
        return null;
    }
}
