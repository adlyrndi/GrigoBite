package com.grigoBiteUI.model;

import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.auth.Pembeli;
import com.grigoBiteUI.model.auth.Penjual;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pesanan")  // Ganti nama tabel sesuai kebutuhan Anda
public class Pesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime waktuPesanan = LocalDateTime.now();

    private String statusPesanan = "Incomplete";

    private double totalPesanan;

    private String statusTransaksi = "Belum Dibayar";

    @ManyToOne
    private Pembeli pembeli;

    @ManyToOne
    private Penjual penjual;

    @OneToMany
    private List<Menu> listMakanan;

    private void calculateTotalPesanan() {
        this.totalPesanan = listMakanan.stream().mapToDouble(Menu::getHarga).sum();
    }
}
