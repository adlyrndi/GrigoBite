package com.grigoBiteUI.dto.canteen;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCUPesanan {
    @NotNull(message = "Pembeli ID cannot be null")
    private Long idPembeli;

    @NotNull(message = "Penjual ID cannot be null")
    private Long idPenjual;

    private List<Long> listMakananIds;
}