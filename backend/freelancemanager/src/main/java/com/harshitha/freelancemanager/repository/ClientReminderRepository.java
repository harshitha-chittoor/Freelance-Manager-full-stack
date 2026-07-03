package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.ClientReminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientReminderRepository
        extends JpaRepository<ClientReminder, Long> {

    Optional<ClientReminder> findByClientId(Long clientId);

}