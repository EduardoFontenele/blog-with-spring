package com.edublog.repository;

import com.edublog.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<Profile, UUID> {
}
