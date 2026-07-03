package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUserId(Long userId);

    Optional<Project> findByIdAndUserId(Long id, Long userId);

    List<Project> findByStatus(String status);

    List<Project> findByStatusContainingIgnoreCase(String status);

    long countByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);

    boolean existsByProposalId(Long proposalId);

    List<Project> findByDeadlineBefore(LocalDate date);

    List<Project> findByDeadlineBetween(LocalDate start, LocalDate end);


}