package com.grigoBiteUI.dto.canteen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCUCanteen {
    private String alamat;
    private String namaKantin;
    private String Fakultas;
}
