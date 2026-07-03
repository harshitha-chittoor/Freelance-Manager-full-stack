package com.harshitha.freelancemanager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String invoiceNumber;

    private Long clientId;

    private Long proposalId;

    private Double amount;

    // KEEP CONSISTENCY WITH YOUR PROJECT STYLE
    private String status;  // PAID / PENDING / OVERDUE

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate createdAt;

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public Long getUserId() {
        return userId;
    }
    // ---------------- SETTERS ----------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}