package com.grigoBiteUI.service;

import com.grigoBiteUI.dto.RequestFeedback;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.Feedback;
import com.grigoBiteUI.repository.FeedbackRepository;
import com.grigoBiteUI.repository.PesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final PesananRepository pesananRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, PesananRepository pesananRepository) {
        this.feedbackRepository = feedbackRepository;
        this.pesananRepository = pesananRepository;
    }

    public Feedback createFeedback(RequestFeedback requestFeedback) {
        // Creating feedback
        Pesanan pesanan = pesananRepository.getReferenceById(requestFeedback.getIdPesanan());

        Feedback feedback = Feedback.builder()
                .idPenjual(requestFeedback.getIdPenjual())
                .idPembeli(requestFeedback.getIdPembeli())
                .rating(requestFeedback.getRating())
                .komentar(requestFeedback.getKomentar())
                .pesanan(pesanan)
                .build();

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public Optional<Pesanan> getPesananById(Long idPesanan){
        return pesananRepository.findById(idPesanan);
    }
    public Feedback getFeedbackByIdPesanan(Long pesananId){
        return feedbackRepository.findByPesananId(pesananId).orElse(null);
    }

    public List<Feedback> getFeedbackByPenjual(Long idPenjual){
        return feedbackRepository.findByIdPenjual(idPenjual);
    }
    // You can add more methods as needed
}

