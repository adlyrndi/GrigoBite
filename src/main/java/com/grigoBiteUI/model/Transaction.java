package com.grigoBiteUI.model;

import com.grigoBiteUI.model.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "\"transaksi\"")  // Ganti nama tabel sesuai kebutuhan Anda
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double saldoPembeliAfter;

    @ManyToOne
    private User userPenjual;

    @ManyToOne
    private User userPembeli;

    private double amount;

    private String description;
}

