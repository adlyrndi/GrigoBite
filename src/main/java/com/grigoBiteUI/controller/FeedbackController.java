package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.RequestFeedback;
import com.grigoBiteUI.model.Feedback;
import com.grigoBiteUI.service.FeedbackService;
import com.grigoBiteUI.service.PesananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final PesananService pesananService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, PesananService pesananService) {
        this.feedbackService = feedbackService;
        this.pesananService = pesananService;
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestBody RequestFeedback requestFeedback) {
        if(pesananService.checkStatusFeedback(requestFeedback)==true){
            Feedback createdFeedback = feedbackService.createFeedback(requestFeedback);
            return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> allFeedback = feedbackService.getAllFeedback();
        return ResponseEntity.ok(allFeedback);
    }

    @GetMapping("/by-id/{feedbackIdPesanan}")
    public ResponseEntity<Feedback> getFeedbackByIdPesanan(@PathVariable Long feedbackIdPesanan) {
        Feedback feedback = feedbackService.getFeedbackByIdPesanan(feedbackIdPesanan);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/penjual/{idPenjual}")
    public ResponseEntity<List<Feedback>> getFeedbackByPenjual(@PathVariable Long idPenjual) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByPenjual(idPenjual);
        return ResponseEntity.ok(feedbackList);
    }

    // You can add more mapping methods as needed

}
