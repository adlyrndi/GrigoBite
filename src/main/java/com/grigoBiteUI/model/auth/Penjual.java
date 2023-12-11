package com.grigoBiteUI.model.auth;

import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.model.Pesanan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
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

