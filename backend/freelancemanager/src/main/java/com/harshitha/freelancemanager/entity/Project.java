package com.harshitha.freelancemanager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long clientId;

    private Long proposalId;

    private String projectName;

    @Column(length = 2000)
    private String description;

    private LocalDate startDate;

    private LocalDate deadline;

    private Integer progress;

    private String status;

    public Project() {
    }

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
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