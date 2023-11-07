package com.edublog.repository;

import com.edublog.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Role, Long> {
    Role findByType(String role);
}
