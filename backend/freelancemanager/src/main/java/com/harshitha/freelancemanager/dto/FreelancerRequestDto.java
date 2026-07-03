package com.harshitha.freelancemanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FreelancerRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Skill cannot be empty")
    private String skill;

    @Min(value = 1000, message = "Salary must be at least 1000")
    private Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}