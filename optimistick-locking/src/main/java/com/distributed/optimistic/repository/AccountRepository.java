package com.distributed.optimistic.repository;

import com.distributed.optimistic.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * PESSIMISTIC WRITE
     * Generates: SELECT ... FOR UPDATE
     */
    @Lock (LockModeType.PESSIMISTIC_WRITE)
    @Query ("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdForUpdate(@Param ("id") Long id);

    /**
     * PESSIMISTIC READ (optional)
     */
    @Lock (LockModeType.PESSIMISTIC_READ)
    @Query ("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdForRead(@Param ("id") Long id);
}
