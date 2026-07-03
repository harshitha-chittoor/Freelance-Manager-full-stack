package com.harshitha.freelancemanager.dto;

import java.time.LocalDate;

public class ProjectResponseDto {

    private Long id;

    private Long clientId;

    private Long proposalId;

    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate deadline;

    private Integer progress;

    private String status;

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Integer getProgress() {
        return progress;
    }

    public String getStatus() {
        return status;
    }

    // ================= SETTERS =================

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}