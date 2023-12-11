package com.grigoBiteUI.service.Payment;

import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.Payment.Transaction;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grigoBiteUI.repository.PesananRepository;
import com.grigoBiteUI.repository.UserRepository;
import com.grigoBiteUI.repository.TransactionRepository;
import com.grigoBiteUI.repository.MenuRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {
    private final PesananRepository pesananRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final MenuRepository menuRepository;


    @Autowired
    public PaymentService(PesananRepository pesananRepository, UserRepository userRepository, TransactionRepository transactionRepository, MenuRepository menuRepository){
        this.pesananRepository = pesananRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.menuRepository = menuRepository;
    }
    public Pesanan findById(long pesananId) {
        return pesananRepository.findById(pesananId);
    }

    public boolean pay(Long pesananId, Long pembeliId, Long penjualId){
        Pesanan pesanan = findById(pesananId);

        if(pesanan.getListMakanan().isEmpty()){
            throw new RuntimeException("Pesanan not found");
        }
        StringBuilder namesString = new StringBuilder();
        for(Menu menu : pesanan.getListMakanan()){
            namesString.append(menu.getNama()).append(", ");
        }
        if (namesString.length() > 0) {
            namesString.delete(namesString.length() - 2, namesString.length());
        } // ini untuk deskirpsi makanan atau menu apa saja yang dibeli

        if(pesanan == null){
            throw new RuntimeException("Pesanan not found");
        }

        User penjual = userRepository.findById(penjualId);
        if(penjual == null){
            throw new RuntimeException("Penjual not found");
        }
        User pembeli = userRepository.findById(pembeliId);
        if(pembeli == null){
            throw new RuntimeException("Pembeli not found");
        }

        double totalAmount = pesanan.getTotalPesanan();
        double saldoPembeli = ((Pembeli)pembeli).getSaldo();

        if (saldoPembeli<totalAmount){
            throw new RuntimeException("Insufficient balance");
        }
        ((Pembeli) pembeli).setSaldo(saldoPembeli-totalAmount);
        pesanan.setStatusTransaksi("Sudah dibayar");
        double saldoPenjual = ((Penjual)penjual).getSaldo();
        ((Penjual) penjual).setSaldo(saldoPenjual+totalAmount);
        userRepository.save(pembeli);
        userRepository.save(penjual);
        pesananRepository.save(pesanan);

        Transaction newTransaction = Transaction.builder().pembeli((Pembeli)pembeli)
                .amount(totalAmount)
                .penjual((Penjual) penjual)
                .description("Membeli "+namesString.toString())
                .build();
        transactionRepository.save(newTransaction);
        return true;
    }
    public boolean topup(Long pembeliId, double amount){
        User pembeli = userRepository.findById(pembeliId);
        if(pembeli == null){
            throw new RuntimeException("Pembeli not found");
        }
        double saldoAwal = ((Pembeli)pembeli).getSaldo();
        ((Pembeli)pembeli).setSaldo(saldoAwal + amount);
        Transaction newTransaction = Transaction.builder().pembeli((Pembeli)pembeli)
                .amount(amount)
                .description("Top up")
                .build();
        transactionRepository.save(newTransaction);
        return true;

    }

    public List<Transaction> viewWallet(Long pembeliId){
        return transactionRepository.findbyIdPembeli(pembeliId);
    }
    public List<Transaction> viewEarnings(Long penjualId){
        return transactionRepository.findbyIdPenjual(penjualId);
    }

}
