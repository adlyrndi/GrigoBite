package com.grigoBiteUI.model.auth;

import com.grigoBiteUI.model.Pesanan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pembeli")
public class Pembeli extends User {

    private double saldo;

    @OneToMany(mappedBy = "pembeli", cascade = CascadeType.ALL)
    private List<Pesanan> listPesanan;

}
