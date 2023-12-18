package com.grigoBiteUI.model.auth;

import com.grigoBiteUI.model.Pesanan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "pembeli")
public class Pembeli extends User {

    private double saldo=0;

    @OneToMany(mappedBy = "pembeli", cascade = CascadeType.ALL)
    private List<Pesanan> listPesanan;

}
