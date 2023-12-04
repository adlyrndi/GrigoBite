package com.grigoBiteUI.service;

import com.grigoBiteUI.dto.canteen.RequestCUPesanan;
import com.grigoBiteUI.dto.canteen.RequestUPesanan;
import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.repository.MenuRepository;
import com.grigoBiteUI.repository.PesananRepository;
import com.grigoBiteUI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PesananService {

    private final PesananRepository pesananRepository;
    private final MenuRepository menuRepository;

    private final UserRepository userRepository;
    @Autowired
    public PesananService(UserRepository userRepository, PesananRepository pesananRepository, MenuRepository menuRepository) {
        this.pesananRepository = pesananRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public Pesanan createPesanan(RequestCUPesanan requestCUPesanan) {
        Pembeli pembeli = (Pembeli) userRepository.findById(requestCUPesanan.getIdPembeli());
        Penjual penjual = (Penjual) userRepository.findById(requestCUPesanan.getIdPenjual());

        List<Menu> listMakanan = menuRepository.findAllById(requestCUPesanan.getListMakananIds());
        List<Pesanan> list = pembeli.getListPesanan();

        Pesanan pesanan = Pesanan.builder()
                .statusPesanan("Incomplete")
                .statusTransaksi("Belum Dibayar")
                .pembeli(pembeli)
                .penjual(penjual)
                .listMakanan(listMakanan)
                .build();

        list.add(pesanan);
        pembeli.setListPesanan(list);

        return pesananRepository.save(pesanan);
    }
    public Pesanan updatePesanan(RequestUPesanan requestUPesanan) {

        Pesanan pesanan = pesananRepository.getReferenceById(requestUPesanan.getPesananId());

        if (!pesanan.getStatusTransaksi().equals("Belum Dibayar")) {
            throw new RuntimeException();
        }
        List<Menu> listMakanan = menuRepository.findAllById(requestUPesanan.getListMakananIds());
        pesanan.setListMakanan(listMakanan);

        return pesananRepository.save(pesanan);
    }

    public List<Pesanan> getAllPesanan() {
        return pesananRepository.findAll();
    }

    public List<Pesanan> findByPembeliId(long pembeliId) {
        return pesananRepository.findByPembeliId(pembeliId);
    }

    public List<Pesanan> findByPenjualId(long penjualId) {
        return pesananRepository.findByPenjualId(penjualId);
    }

    public List<Pesanan> getPesananIncompleteFromPembeli(long idPembeli) {
        List<Pesanan> listPesanan = new ArrayList<>();
        List<Pesanan> listPesananPembeli = findByPembeliId(idPembeli);

        for (Pesanan pesanan:listPesananPembeli) {
            if (pesanan.getStatusTransaksi().equals("Belum Dibayar")) {
                listPesanan.add(pesanan);
            }
        }

        return listPesanan;
    }

    public void deletePesanan(long idPesanan) {
        pesananRepository.deleteById(idPesanan);
    }

}
