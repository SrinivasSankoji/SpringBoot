package com.distributed.optimistic.service;

import com.distributed.optimistic.dto.AccountResponse;
import com.distributed.optimistic.entity.Account;
import com.distributed.optimistic.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository repo) {
        this.accountRepository = repo;
    }

    @Transactional (readOnly = true)
    public AccountResponse getAccount(Long id) {
        Account a = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found: " + id));
        return toResponse(a);
    }

    /**
     * Endpoint helper: normal update (still protected by @Version).
     */
    @Transactional
    public AccountResponse updateBalance(Long id, BigDecimal newBalance) {
        Account a = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found: " + id));
        a.setBalance(newBalance);
        // flush occurs at commit; version increments automatically
        return toResponse(a);
    }

    /**
     * This method is designed to reproduce optimistic locking failures.
     * Call it twice concurrently (same account id) with overlapSleepMs > 0.
     */
    @Transactional
    public AccountResponse updateBalanceWithOverlap(Long id, BigDecimal newBalance, long overlapSleepMs) {
        Account a = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found: " + id));
        // Read current version for demonstration (not required, but useful for logs/understanding)
        Long loadedVersion = a.getVersion();
        // Change state in persistence context
        a.setBalance(newBalance);
        // Sleep to keep TX open so another request can load the same version and compete
        sleep(overlapSleepMs);
        // At commit time, Hibernate issues:
        // UPDATE accounts SET balance=?, version=? WHERE id=? AND version=?
        // If another TX committed first, WHERE version=? fails -> 0 rows -> OptimisticLock exception
        try {
            return toResponse(a);
        } catch (ObjectOptimisticLockingFailureException e) {
            // Re-throw to be handled in controller advice or controller directly
            throw e;
        } finally {
            // optional: you could log loadedVersion if desired
            System.out.println("Loaded version was: " + loadedVersion);
        }
    }

    /**
     * PESSIMISTIC LOCK example
     * Only ONE transaction can update this row at a time.
     */
    @Transactional
    public AccountResponse updateBalanceWithPessimisticLock(
            Long accountId,
            BigDecimal newBalance,
            long sleepMs
    ) {

        // 1️⃣ Row is locked here (SELECT FOR UPDATE)
        Account account = accountRepository.findByIdForUpdate(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // 2️⃣ Simulate long business processing
        sleep(sleepMs);

        // 3️⃣ Update balance
        account.setBalance(newBalance);

        // 4️⃣ Commit releases the DB lock
        return toResponse(account);
    }

    private static void sleep(long ms) {
        if (ms <= 0) return;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while sleeping", e);
        }
    }

    private static AccountResponse toResponse(Account a) {
        return new AccountResponse(a.getId(), a.getOwner(), a.getBalance(), a.getVersion());
    }
}
