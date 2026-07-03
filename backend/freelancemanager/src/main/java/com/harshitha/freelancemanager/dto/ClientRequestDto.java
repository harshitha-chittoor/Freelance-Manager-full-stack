package com.harshitha.freelancemanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientRequestDto {

    @NotBlank(message = "Client name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    // Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}