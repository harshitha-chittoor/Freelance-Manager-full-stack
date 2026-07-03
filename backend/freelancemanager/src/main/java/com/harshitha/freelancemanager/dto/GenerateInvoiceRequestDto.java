package com.harshitha.freelancemanager.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class GenerateInvoiceRequestDto {

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}