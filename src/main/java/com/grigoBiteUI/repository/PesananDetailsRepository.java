package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.PesananDetails;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesananDetailsRepository extends JpaRepository<PesananDetails, Long> {

    List<PesananDetails> findAllByPesanan(Pesanan pesanan);

}
