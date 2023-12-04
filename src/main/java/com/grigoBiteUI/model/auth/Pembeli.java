package com.grigoBiteUI.model.auth;

import com.grigoBiteUI.model.Pesanan;
import com.grigoBiteUI.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pembeli")
public class Pembeli extends User {

    private double saldo;

    @OneToMany(mappedBy = "pembeli", cascade = CascadeType.ALL)
    private List<Pesanan> listPesanan;

}
