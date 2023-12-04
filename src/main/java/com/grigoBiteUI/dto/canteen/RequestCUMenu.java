package com.grigoBiteUI.dto.canteen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCUMenu {
    private String nama;
    private String deskripsi;
    private int harga;
}
