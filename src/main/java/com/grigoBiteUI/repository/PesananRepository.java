package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.Pesanan;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesananRepository extends JpaRepository<Pesanan, Long> {

    @NonNull
    List<Pesanan> findAll();


    Pesanan getById(long id);

    List<Pesanan> findByPembeliId(long pembeliId);

    List<Pesanan> findByPenjualId(long penjualId);

    void deleteById(long id);
}
