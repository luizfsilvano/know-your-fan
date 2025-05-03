package com.furia.knowyourfan.domain.repository;

import com.furia.knowyourfan.domain.model.StreamerLiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.Optional;


public interface StreamerLiveStatusRepository extends JpaRepository<StreamerLiveStatus, UUID> {
    @Query("SELECT s.link FROM StreamerLiveStatus s WHERE LOWER(s.name) = LOWER(:name)")
    Optional<String> findLinkByNameIgnoreCase(String name);
}