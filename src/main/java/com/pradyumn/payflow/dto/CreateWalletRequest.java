package com.pradyumn.payflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWalletRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
    
}
