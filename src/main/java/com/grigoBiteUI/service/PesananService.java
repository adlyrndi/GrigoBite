package com.grigoBiteUI.service;

import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.repository.MenuRepository;
import com.grigoBiteUI.repository.PesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesananService {

    private final PesananRepository pesananRepository;
    private final MenuRepository menuRepository;
    @Autowired
    public PesananService(PesananRepository pesananRepository, MenuRepository menuRepository) {
        this.pesananRepository = pesananRepository;
        this.menuRepository = menuRepository;
    }

    public Pesanan createPesanan(Pembeli pembeli,Penjual penjual, List<Long> listMakananIds) {
        List<Menu> listMakanan = menuRepository.findAllById(listMakananIds);

        Pesanan pesanan = Pesanan.builder()
                .statusPesanan("Incomplete")
                .statusTransaksi("Belum Dibayar")
                .pembeli(pembeli)
                .penjual(penjual)
                .listMakanan(listMakanan)
                .build();
        return pesananRepository.save(pesanan);
    }

    public List<Pesanan> getAllPesanan() {
        return pesananRepository.findAll();
    }

    public Pesanan findById(long pesananId) {
        return pesananRepository.findById(pesananId);
    }

    public List<Pesanan> findByPembeliId(long pembeliId) {
        return pesananRepository.findByPembeliId(pembeliId);
    }

    public List<Pesanan> findByPenjualId(long penjualId) {
        return pesananRepository.findByPenjualId(penjualId);
    }

}
