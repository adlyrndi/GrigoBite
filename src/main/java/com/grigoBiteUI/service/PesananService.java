package com.grigoBiteUI.service;

import com.grigoBiteUI.dto.canteen.RequestCUPesanan;
import com.grigoBiteUI.dto.canteen.RequestUPesanan;
import com.grigoBiteUI.dto.RequestFeedback;
import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.PesananDetails;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.repository.MenuRepository;
import com.grigoBiteUI.repository.PesananRepository;
import com.grigoBiteUI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public  PesananDetails createFromList(List<Object> list) {
        Optional<Menu> optMenu = Optional.ofNullable(menuRepository.findById((Integer) list.get(0)));
        if (optMenu.isPresent()) {
            Menu menu = optMenu.get();
            PesananDetails pesananDetails = new PesananDetails();
            pesananDetails.setMenu(menu);
            pesananDetails.setQuantity((Integer) list.get(1));
            pesananDetails.setSubTotal(Long.valueOf((Integer) list.get(2)));
            return pesananDetails;
        }
        return null;
    }

    public Pesanan createPesanan(RequestCUPesanan requestCUPesanan) {
        System.out.println(requestCUPesanan);
        Pembeli pembeli = (Pembeli) userRepository.findById(Long.parseLong(requestCUPesanan.getIdPembeli()));
        Penjual penjual = (Penjual) userRepository.findById(Long.parseLong(requestCUPesanan.getIdPenjual()));

        List<List<Object>> listMakanan = requestCUPesanan.getMenuItems();
        List<Pesanan> listPembeli = pembeli.getListPesanan();
        List<Pesanan> listPenjual = penjual.getListPesanan();

        List<PesananDetails> listPesananDetails = new ArrayList<>();


        for (List<Object> listItem : listMakanan) {
            PesananDetails pesananDetails = createFromList(listItem);
            listPesananDetails.add(pesananDetails);
        }



        Pesanan pesanan = Pesanan.builder()
                .statusPesanan("Incomplete")
                .statusTransaksi("Belum Dibayar")
                .pembeli(pembeli)
                .penjual(penjual)
                .listPesananDetails(listPesananDetails)
                .build();

        listPembeli.add(pesanan);
        listPenjual.add(pesanan);

        pembeli.setListPesanan(listPembeli);
        penjual.setListPesanan(listPenjual);

        return pesananRepository.save(pesanan);
    }
    public Pesanan updatePesanan(RequestUPesanan requestUPesanan) {

        Pesanan pesanan = pesananRepository.getReferenceById(requestUPesanan.getPesananId());

        List<PesananDetails> listMakanan = requestUPesanan.getMenuItems();
        pesanan.setListPesananDetails(listMakanan);

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

    public boolean checkStatusFeedback(RequestFeedback requestFeedback){
        Pesanan pesanan = pesananRepository.getReferenceById(requestFeedback.getIdPesanan());


        if ((pesanan.getStatusTransaksi().equals("Sudah Dibayar")) && (pesanan.getStatusPesanan().equals("Complete"))){
            return true;
        }else{
            return false;
        }
    }


}
