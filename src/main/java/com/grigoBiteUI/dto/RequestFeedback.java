package com.grigoBiteUI.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestFeedback {

    private Long idPesanan;
    @NotNull(message = "Pembeli ID cannot be null")
    private Long idPembeli;

    @NotNull(message = "Penjual ID cannot be null")
    private Long idPenjual;

    @NotNull(message = "Rating is still empty")
    private int rating;

    @NotNull(message = "Comment is still empty")
    private String komentar;
}
