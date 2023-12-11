package com.grigoBiteUI.model.auth;

import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.CanteenList.Tenant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
t@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Penjual extends User {

    private double saldo;

    @OneToOne
    private Tenant tenant;

    @OneToMany(mappedBy = "penjual", cascade = CascadeType.ALL)
    private List<Pesanan> listPesanan;


}

