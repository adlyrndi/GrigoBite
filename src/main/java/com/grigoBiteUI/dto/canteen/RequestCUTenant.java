package com.grigoBiteUI.dto.canteen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCUTenant {
    private String namaTenant;
    private String deskripsiTenant;

    private Integer idPenjual;
}