package com.distributed.optimistic.controller;

import com.distributed.optimistic.dto.AccountResponse;
import com.distributed.optimistic.dto.UpdateBalanceRequest;
import com.distributed.optimistic.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping ("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping (value = "/{id}", produces = "application/json")
    public AccountResponse get(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @PutMapping ("/{id}/balance")
    public AccountResponse updateBalance(@PathVariable Long id,
                                         @RequestBody @Valid UpdateBalanceRequest request) {
        return service.updateBalance(id, request.getNewBalance());
    }

    /**
     * Designed for reproducing optimistic lock issues.
     * Example:
     * PUT /accounts/1/balance/overlap?sleepMs=5000
     */
    @PutMapping ("/{id}/balance/overlap")
    public AccountResponse updateBalanceWithOverlap(@PathVariable Long id,
                                                    @RequestParam (defaultValue = "4000") long sleepMs,
                                                    @RequestBody @Valid UpdateBalanceRequest request) {
        return service.updateBalanceWithOverlap(id, request.getNewBalance(), sleepMs);
    }

    /**
     * Example:
     * PUT /accounts/1/balance/pessimistic?sleepMs=5000
     */
    @PutMapping ("/{id}/balance/pessimistic")
    public AccountResponse updateBalancePessimistic(
            @PathVariable Long id,
            @RequestParam (defaultValue = "5000") long sleepMs,
            @RequestBody UpdateBalanceRequest request
    ) {
        return service.updateBalanceWithPessimisticLock(
                id,
                request.getNewBalance(),
                sleepMs
        );
    }

    /**
     * Simple mapping to show optimistic lock failure clearly.
     */
    @ExceptionHandler (ObjectOptimisticLockingFailureException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    public Map<String, Object> handleOptimisticLock(ObjectOptimisticLockingFailureException ex) {
        return Map.of(
                "error", "OPTIMISTIC_LOCK_CONFLICT",
                "message", "Concurrent update detected. Please retry.",
                "exception", ex.getClass().getSimpleName()
        );
    }
}
