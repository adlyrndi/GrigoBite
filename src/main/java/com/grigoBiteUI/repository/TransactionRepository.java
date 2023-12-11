package com.grigoBiteUI.repository;
import com.grigoBiteUI.model.Payment.Transaction;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction>findbyIdPembeli(Long idPembeli);

    List<Transaction>findbyIdPenjual(Long idPenjual);
}
