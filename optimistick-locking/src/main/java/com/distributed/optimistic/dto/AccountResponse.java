package com.distributed.optimistic.dto;

import java.math.BigDecimal;


public record AccountResponse(Long id, String owner, BigDecimal balance, Long version) {
}
