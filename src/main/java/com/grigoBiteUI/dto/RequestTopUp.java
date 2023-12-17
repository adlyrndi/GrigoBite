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
public class RequestTopUp {

    @NotNull()
    private Long idPembeli;

    @NotNull()
    private int amount;

}
