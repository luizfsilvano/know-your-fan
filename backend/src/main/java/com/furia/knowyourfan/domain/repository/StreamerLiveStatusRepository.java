package com.furia.knowyourfan.domain.repository;

import com.furia.knowyourfan.domain.model.StreamerLiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StreamerLiveStatusRepository extends JpaRepository<StreamerLiveStatus, UUID> {
    Optional<StreamerLiveStatus> findByNameIgnoreCase(String name);
}
