package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ApiResponse;
import com.harshitha.freelancemanager.dto.FreelancerRequestDto;
import com.harshitha.freelancemanager.dto.FreelancerResponseDto;
import com.harshitha.freelancemanager.service.FreelancerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> getAllFreelancers() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Fetched all freelancers",
                        freelancerService.getAllFreelancers()
                )
        );
    }

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public ResponseEntity<ApiResponse<FreelancerResponseDto>> addFreelancer(
            @Valid @RequestBody FreelancerRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Freelancer created successfully",
                                freelancerService.addFreelancer(dto)
                        )
                );
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FreelancerResponseDto>> getFreelancerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Freelancer fetched successfully",
                        freelancerService.getFreelancerById(id)
                )
        );
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FreelancerResponseDto>> updateFreelancer(
            @PathVariable Long id,
            @Valid @RequestBody FreelancerRequestDto dto) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Freelancer updated successfully",
                        freelancerService.updateFreelancer(id, dto)
                )
        );
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFreelancer(
            @PathVariable Long id) {

        freelancerService.deleteFreelancer(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Freelancer deleted successfully",
                        "Deleted"
                )
        );
    }

    // =========================
    // SEARCH - SKILL
    // =========================
    @GetMapping("/search/skill")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchBySkill(
            @RequestParam String skill) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search by skill completed",
                        freelancerService.searchBySkill(skill)
                )
        );
    }

    // =========================
    // SEARCH - NAME
    // =========================
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search by name completed",
                        freelancerService.searchByName(name)
                )
        );
    }

    // =========================
    // PARTIAL NAME SEARCH
    // =========================
    @GetMapping("/search/name/partial")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchByNamePartial(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Partial name search completed",
                        freelancerService.searchByNamePartial(name)
                )
        );
    }

    // =========================
    // PARTIAL SKILL SEARCH
    // =========================
    @GetMapping("/search/skill/partial")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchBySkillPartial(
            @RequestParam String skill) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Partial skill search completed",
                        freelancerService.searchBySkillPartial(skill)
                )
        );
    }

    // =========================
    // SALARY SEARCH
    // =========================
    @GetMapping("/search/salary")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchBySalary(
            @RequestParam Double salary) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Salary search completed",
                        freelancerService.searchBySalary(salary)
                )
        );
    }

    // =========================
    // IGNORE CASE NAME SEARCH
    // =========================
    @GetMapping("/search/name/ignore-case")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchByNameIgnoreCase(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Case-insensitive search completed",
                        freelancerService.searchByNameIgnoreCase(name)
                )
        );
    }

    // =========================
    // NAME + SKILL SEARCH
    // =========================
    @GetMapping("/search/name-skill")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchByNameAndSkill(
            @RequestParam String name,
            @RequestParam String skill) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Combined search completed",
                        freelancerService.searchByNameAndSkill(name, skill)
                )
        );
    }


    // SALARY RANGE SEARCH

    @GetMapping("/search/salary-range")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> searchBySalaryBetween(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Salary range search completed",
                        freelancerService.searchBySalaryBetween(minSalary, maxSalary)
                )
        );
    }

    // =========================
    // SORTING
    // =========================
    @GetMapping("/sort")
    public ResponseEntity<ApiResponse<List<FreelancerResponseDto>>> getSortedFreelancers(
            @RequestParam String field,
            @RequestParam String direction) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Sorting completed",
                        freelancerService.getFreelancersSorted(field, direction)
                )
        );
    }

    // =========================
    // PAGINATION
    // =========================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<FreelancerResponseDto>>> getPaginatedFreelancers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Pagination completed",
                        freelancerService.getFreelancersPaginated(page, size, field, direction)
                )
        );
    }

    // =========================
    // FILTER BY SKILL + PAGINATION
    // =========================
    @GetMapping("/filter/skill")
    public ResponseEntity<ApiResponse<Page<FreelancerResponseDto>>> filterBySkill(
            @RequestParam String skill,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Skill filtering completed",
                        freelancerService.filterBySkill(skill, page, size, field, direction)
                )
        );
    }
}