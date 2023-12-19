package com.grigoBiteUI.service;
import com.grigoBiteUI.dto.RequestPayment;
import com.grigoBiteUI.dto.RequestTopUp;
import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.Transaction;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.repository.MenuRepository;
import com.grigoBiteUI.repository.PesananRepository;
import com.grigoBiteUI.repository.TransactionRepository;
import com.grigoBiteUI.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentService {
    private final PesananRepository pesananRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final MenuRepository menuRepository;


    @Autowired
    public PaymentService(PesananRepository pesananRepository, UserRepository userRepository, TransactionRepository transactionRepository, MenuRepository menuRepository) {
        this.pesananRepository = pesananRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.menuRepository = menuRepository;
    }

    public Transaction pay(RequestPayment requestPayment) {
        Pembeli pembeli = (Pembeli) userRepository.findById(requestPayment.getIdPembeli());
        Penjual penjual = (Penjual) userRepository.findById(requestPayment.getIdPenjual());
        double saldoPembeliBefore =  pembeli.getSaldo();

        StringBuilder namesString = new StringBuilder();
//        for (Menu menu : requestPayment.getPesanan().getListMakanan()) {
//            namesString.append(menu.getNama()).append(", ");
//        }
        if (namesString.length() > 0) {
            namesString.delete(namesString.length() - 2, namesString.length());
        } // ini untuk deskirpsi makanan atau menu apa saja yang dibeli

        if(saldoPembeliBefore<requestPayment.getAmount()){
            pembeli.setSaldo(requestPayment.getAmount()-saldoPembeliBefore);
            Transaction transaction = Transaction.builder()
                    .amount(requestPayment.getAmount())
                    .description("Membeli " + namesString.toString())
                    .userPenjual(penjual)
                    .userPembeli(pembeli)
                    .build();
            penjual.setSaldo(penjual.getSaldo()+requestPayment.getAmount());
            return  transactionRepository.save(transaction);
        }
        else{
            throw new RuntimeException("Insufficient balance");
        }
    }

    public Transaction topup(@Valid RequestTopUp requestTopUp) {
        Pembeli pembeli = (Pembeli) userRepository.findById(requestTopUp.getIdPembeli());
        double saldoAwal = pembeli.getSaldo();
        pembeli.setSaldo(saldoAwal+requestTopUp.getAmount());
        Transaction newTransaction = Transaction.builder().userPembeli(pembeli)
                .amount(requestTopUp.getAmount())
                .description("Top up")
                .build();
        transactionRepository.save(newTransaction);
        return newTransaction;

    }

    public Optional<Transaction> viewWallet(Long pembeliId) {
        User pembeli = userRepository.findById(pembeliId);
        return transactionRepository.findByUserPembeli(pembeli);
    }

    public Optional<Transaction> viewEarnings(Long penjualId) {
        User penjual = userRepository.findById(penjualId);
        return transactionRepository.findByUserPenjual(penjual);
    }
}
