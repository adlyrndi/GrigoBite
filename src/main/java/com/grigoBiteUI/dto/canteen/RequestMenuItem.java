package com.grigoBiteUI.dto.canteen;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestMenuItem {
    @NotNull(message = "Menu ID cannot be null")
    private Long menuId;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "subTotal cannot be null")
    private Long subTotal;


}
