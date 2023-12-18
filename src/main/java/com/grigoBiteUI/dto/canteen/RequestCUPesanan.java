package com.grigoBiteUI.dto.canteen;

import com.grigoBiteUI.model.PesananDetails;
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

    private String idPembeli;

    private String idPenjual;

    private List<List<Object>> menuItems;
}