package com.furia.knowyourfan.domain.repository;

import com.furia.knowyourfan.domain.model.FuriaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FuriaEventRepository extends JpaRepository<FuriaEvent, UUID> {
    @Query("SELECT e FROM FuriaEvent e WHERE e.dateTime >= :now ORDER BY e.dateTime ASC")
    List<FuriaEvent> findUpcomingEvents(LocalDateTime now);
}
