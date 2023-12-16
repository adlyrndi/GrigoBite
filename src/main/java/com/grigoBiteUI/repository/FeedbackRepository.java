package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // You can add custom query methods if needed


    List<Feedback> findByIdPenjual(Long idPenjual);
    Optional<Feedback> findById(Long id);

    Optional<Feedback> findByPesananId(Long idPesanan);
}