package com.furia.knowyourfan.domain.repository;

import com.furia.knowyourfan.domain.model.FuriaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FuriaEventRepository extends JpaRepository<FuriaEvent, UUID> {

    List<FuriaEvent> findAllByDateTimeAfterOrderByDateTimeAsc(LocalDateTime now);
}
