package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.canteen.RequestCUPesanan;
import com.grigoBiteUI.dto.canteen.RequestUPesanan;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.service.PesananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pesanans")
public class PesananController {

    private final PesananService pesananService;

    @Autowired
    public PesananController(PesananService pesananService) {
        this.pesananService = pesananService;
    }

    @GetMapping("/pembeli/{id}")
    public String cart(Model model, @PathVariable Long id) {
        // Gunakan orderId untuk mendapatkan data pesanan sesuai kebutuhan
        List<Pesanan> pesanan = pesananService.getPesananIncompleteFromPembeli(id);
        // Tambahkan data pesanan ke model untuk digunakan di halaman
        model.addAttribute("pesanan", pesanan);

        return "cart";
    }

    @PostMapping("/create")
    public ResponseEntity<Pesanan> createPesanan(@RequestBody RequestCUPesanan requestCUPesanan) {
        Pesanan pesanan = pesananService.createPesanan(requestCUPesanan);
        return new ResponseEntity<>(pesanan, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pesanan> updatePesanan(@RequestBody RequestUPesanan requestUPesanan) {
        Pesanan pesanan = pesananService.updatePesanan(requestUPesanan);
        return new ResponseEntity<>(pesanan, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pesanan>> getAllPesanan() {
        List<Pesanan> pesanans = pesananService.getAllPesanan();
        return new ResponseEntity<>(pesanans, HttpStatus.OK);
    }


}
