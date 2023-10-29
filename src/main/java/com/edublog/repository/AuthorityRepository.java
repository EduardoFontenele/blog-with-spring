package com.edublog.repository;

import com.edublog.domain.enums.AuthorityTable;
import com.edublog.domain.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByType(String role);
}
