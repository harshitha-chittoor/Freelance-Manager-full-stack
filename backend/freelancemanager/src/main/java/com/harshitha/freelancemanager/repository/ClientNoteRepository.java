package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.ClientNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientNoteRepository extends JpaRepository<ClientNote, Long> {

    List<ClientNote> findByClientIdOrderByHighlightedDescCreatedAtDesc(Long clientId);

}