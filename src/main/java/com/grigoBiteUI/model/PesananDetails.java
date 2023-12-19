package com.grigoBiteUI.model;


import com.grigoBiteUI.model.CanteenList.Menu;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pesanan_details")  // Ganti nama tabel sesuai kebutuhan Anda
public class PesananDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Menu menu;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "SubTotal cannot be null")
    private Long subTotal;

    @ManyToOne
    private Pesanan pesanan;




}
