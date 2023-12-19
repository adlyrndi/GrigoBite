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

    private Long idPembeli;


    private Long idPenjual;

    @NotNull(message = "Rating is still empty")
    private int rating;

    @NotNull(message = "Comment is still empty")
    private String komentar;
}
