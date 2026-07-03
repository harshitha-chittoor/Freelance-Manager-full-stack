package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    List<Proposal> findByStatus(String status);

    List<Proposal> findByStatusContainingIgnoreCase(String status);

    List<Proposal> findByUserId(Long userId);

    Optional<Proposal> findByIdAndUserId(Long id, Long userId);
}
