package com.grigoBiteUI.dto;

import com.grigoBiteUI.model.Pesanan;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPayment {

    @NotNull(message = "Pembeli ID cannot be null")
    private Long idPembeli;

    @NotNull(message = "Penjual ID cannot be null")
    private Long idPenjual;
    @NotNull()
    private int amount;

    @NotNull()
    private int saldoPembeli;

    @NotNull()
    private Pesanan pesanan;
}
