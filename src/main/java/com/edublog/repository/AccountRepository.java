package com.edublog.repository;

import com.edublog.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Account acc SET acc.isEnabled = false WHERE acc.id = :passedId")
    void disableAccount(@Param("passedId") Long id);

}
