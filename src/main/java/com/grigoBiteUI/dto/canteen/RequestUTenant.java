package com.grigoBiteUI.dto.canteen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUTenant {
    private String namaTenant;
    private String deskripsiTenant;
}