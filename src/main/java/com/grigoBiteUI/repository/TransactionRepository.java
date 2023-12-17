package com.grigoBiteUI.repository;
import com.grigoBiteUI.model.Transaction;
import com.grigoBiteUI.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, User> {

    @NonNull
    Optional<Transaction> findByUserPenjual(User userPenjual);

    @NonNull
    Optional<Transaction> findByUserPembeli(User userPembeli);
}
