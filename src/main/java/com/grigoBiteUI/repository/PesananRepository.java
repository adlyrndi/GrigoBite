package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.Pesanan;
import lombok.NonNull;
import org.checkerframework.checker.index.qual.NonNegative;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PesananRepository extends JpaRepository<Pesanan, Long> {

    @NonNull
    List<Pesanan> findAll();

    Pesanan findById(long id);

    List<Pesanan> findByPembeliId(long pembeliId);

    List<Pesanan> findByPenjualId(long penjualId);
}
