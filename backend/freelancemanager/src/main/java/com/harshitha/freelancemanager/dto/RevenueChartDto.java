package com.harshitha.freelancemanager.dto;

public class RevenueChartDto {

    private String month;
    private Double revenue;

    public RevenueChartDto() {}

    public RevenueChartDto(String month, Double revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}