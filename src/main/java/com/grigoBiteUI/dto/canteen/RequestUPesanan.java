package com.grigoBiteUI.dto.canteen;

import com.grigoBiteUI.model.PesananDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUPesanan {
    private Long pesananId;
    private List<PesananDetails> menuItems;
}
