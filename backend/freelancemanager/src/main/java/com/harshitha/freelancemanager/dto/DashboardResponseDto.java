package com.harshitha.freelancemanager.dto;

public class DashboardResponseDto {

    private Double totalRevenue;
    private Double pendingRevenue;

    private Long totalClients;
    private Long totalInvoices;

    private Long overdueInvoices;

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getPendingRevenue() {
        return pendingRevenue;
    }

    public void setPendingRevenue(Double pendingRevenue) {
        this.pendingRevenue = pendingRevenue;
    }

    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }

    public Long getTotalInvoices() {
        return totalInvoices;
    }

    public void setTotalInvoices(Long totalInvoices) {
        this.totalInvoices = totalInvoices;
    }

    public Long getOverdueInvoices() {
        return overdueInvoices;
    }

    public void setOverdueInvoices(Long overdueInvoices) {
        this.overdueInvoices = overdueInvoices;
    }
}