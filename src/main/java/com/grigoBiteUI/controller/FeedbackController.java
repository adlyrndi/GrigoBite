package com.grigoBiteUI.controller;

import com.grigoBiteUI.model.Feedback;
import com.grigoBiteUI.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(
            @RequestParam Long idPenjual,
            @RequestParam Long idPembeli,
            @RequestParam Long idPesanan,
            @RequestParam int rating,
            @RequestParam String komentar
    ) {
        Feedback createdFeedback = feedbackService.createFeedback(idPenjual, idPembeli, idPesanan, rating, komentar);
        return ResponseEntity.ok(createdFeedback);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> allFeedback = feedbackService.getAllFeedback();
        return ResponseEntity.ok(allFeedback);
    }

    @GetMapping("/by-id/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/penjual/{idPenjual}")
    public ResponseEntity<List<Feedback>> getFeedbackByPenjual(@PathVariable Long idPenjual) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByPenjual(idPenjual);
        return ResponseEntity.ok(feedbackList);
    }

    // You can add more mapping methods as needed

}
