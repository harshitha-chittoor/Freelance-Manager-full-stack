package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    List<Freelancer> findBySkill(String skill);
    List<Freelancer> findByName(String name);
    List<Freelancer> findByNameContaining(String name);
    List<Freelancer> findBySkillContaining(String skill);
    List<Freelancer> findBySalary(double salary);
    List<Freelancer> findByNameAndSkill(String name, String skill);
    List<Freelancer> findByNameIgnoreCase(String name);
    List<Freelancer> findBySalaryBetween(
            double minSalary,
            double maxSalary
    );
    List<Freelancer> findByNameContainingIgnoreCase(String name);
    Page<Freelancer> findBySkill(
            String skill,
            Pageable pageable
    );
}