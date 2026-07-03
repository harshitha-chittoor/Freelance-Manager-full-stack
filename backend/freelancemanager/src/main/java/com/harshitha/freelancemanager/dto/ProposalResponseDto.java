package com.harshitha.freelancemanager.dto;

public class ProposalResponseDto {

    private Long id;

    private String title;

    private String description;

    private Double amount;

    private String status;

    private Long clientId;

    // Getters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Long getClientId() {
        return clientId;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    private boolean invoiceGenerated;

    public boolean isInvoiceGenerated() {
        return invoiceGenerated;
    }

    public void setInvoiceGenerated(boolean invoiceGenerated) {
        this.invoiceGenerated = invoiceGenerated;
    }

    private boolean projectCreated;

    public boolean isProjectCreated() {
        return projectCreated;
    }

    public void setProjectCreated(boolean projectCreated) {
        this.projectCreated = projectCreated;
    }
}