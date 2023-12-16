package com.grigoBiteUI.model;

import com.grigoBiteUI.model.Pesanan;
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
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPenjual;
    private Long idPembeli;
    private int rating;
    private String komentar;

    @OneToOne
    private Pesanan pesanan;
}

