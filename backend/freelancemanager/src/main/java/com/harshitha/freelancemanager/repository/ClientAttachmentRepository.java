package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.ClientAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientAttachmentRepository
        extends JpaRepository<ClientAttachment, Long> {

    List<ClientAttachment> findByClientId(Long clientId);

}