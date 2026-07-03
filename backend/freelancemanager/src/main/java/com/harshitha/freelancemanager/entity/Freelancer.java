package com.harshitha.freelancemanager.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "freelancers")
public class Freelancer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Skill cannot be empty")
    private String skill;
    @Min(value = 1000, message = "Salary must be at least 1000")
    private double salary;
    public Freelancer(){

    }
    public Freelancer(Long id,String name,String skill,double salary){
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.salary = salary;
    }
    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
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