package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ProjectRequestDto;
import com.harshitha.freelancemanager.dto.ProjectResponseDto;
import com.harshitha.freelancemanager.entity.Project;
import com.harshitha.freelancemanager.entity.Proposal;
import com.harshitha.freelancemanager.exception.ProposalNotFoundException;
import com.harshitha.freelancemanager.repository.ProjectRepository;
import com.harshitha.freelancemanager.repository.ProposalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    // ================= DTO =================

    private ProjectResponseDto convertToDto(Project project) {

        ProjectResponseDto dto = new ProjectResponseDto();

        dto.setId(project.getId());
        dto.setProposalId(project.getProposalId());
        dto.setClientId(project.getClientId());

        dto.setProjectName(project.getProjectName());
        dto.setDescription(project.getDescription());

        dto.setStartDate(project.getStartDate());
        dto.setDeadline(project.getDeadline());

        dto.setStatus(project.getStatus());
        dto.setProgress(project.getProgress());

        return dto;
    }

    // ================= CREATE =================

    public ProjectResponseDto createProject(ProjectRequestDto dto, Long userId) {

        Proposal proposal = proposalRepository
                .findById(dto.getProposalId())
                .orElseThrow(() ->
                        new ProposalNotFoundException(
                                "Proposal not found with id " + dto.getProposalId()));

        if (!proposal.getStatus().equalsIgnoreCase("ACCEPTED")) {
            throw new RuntimeException(
                    "Project can only be created for ACCEPTED proposals");
        }

        Project project = new Project();
        project.setId(null); // FORCE CLEAN INSERT

        project.setUserId(userId);

        project.setProposalId(proposal.getId());
        project.setClientId(proposal.getClientId());

        project.setProjectName(dto.getProjectName());
        project.setDescription(dto.getDescription());

        project.setStartDate(LocalDate.now());
        project.setDeadline(dto.getDeadline());

        project.setStatus("NOT_STARTED");
        project.setProgress(0);

        return convertToDto(projectRepository.save(project));
    }

    // ================= GET ALL =================

    public List<ProjectResponseDto> getAllProjects(Long userId) {

        return projectRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================

    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        return convertToDto(project);
    }

    // ================= DELETE =================

    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        projectRepository.delete(project);
    }

    // ================= UPDATE =================

    public ProjectResponseDto updateProject(Long id,
                                            ProjectRequestDto dto) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setProjectName(dto.getProjectName());
        project.setDescription(dto.getDescription());
        project.setDeadline(dto.getDeadline());

        return convertToDto(projectRepository.save(project));
    }

    // ================= STATUS =================

    public ProjectResponseDto updateStatus(Long id,
                                           String status) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setStatus(status);

        return convertToDto(projectRepository.save(project));
    }

    // ================= PROGRESS =================

    public ProjectResponseDto updateProgress(Long id,
                                             Integer progress) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setProgress(progress);

        if (progress == 0) {
            project.setStatus("NOT_STARTED");
        }
        else if (progress < 100) {
            project.setStatus("IN_PROGRESS");
        }
        else {
            project.setStatus("COMPLETED");
        }

        return convertToDto(projectRepository.save(project));
    }
}