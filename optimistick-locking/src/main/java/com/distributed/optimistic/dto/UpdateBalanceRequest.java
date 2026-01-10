package com.distributed.optimistic.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateBalanceRequest {

    @NotNull
    private BigDecimal newBalance;

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
}
