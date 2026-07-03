package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ProjectRequestDto;
import com.harshitha.freelancemanager.dto.ProjectResponseDto;
import com.harshitha.freelancemanager.security.JwtUtil;
import com.harshitha.freelancemanager.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= JWT Helper =================

    private Long extractUserId(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }

        String token = authHeader.substring(7);

        return jwtUtil.extractUserId(token);
    }

    // ================= CREATE =================

    @PostMapping
    public ProjectResponseDto createProject(
            @RequestBody ProjectRequestDto dto,
            HttpServletRequest request
    ) {
        Long userId = extractUserId(request);
        return projectService.createProject(dto, userId);
    }

    // ================= GET ALL =================

    @GetMapping
    public List<ProjectResponseDto> getAllProjects(
            HttpServletRequest request) {

        Long userId = extractUserId(request);

        return projectService.getAllProjects(userId);
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto dto) {

        return projectService.updateProject(id, dto);
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return "Project deleted successfully";
    }

    // ================= UPDATE STATUS =================

    @PutMapping("/{id}/status")
    public ProjectResponseDto updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return projectService.updateStatus(id, status);
    }

    // ================= UPDATE PROGRESS =================

    @PutMapping("/{id}/progress")
    public ProjectResponseDto updateProgress(
            @PathVariable Long id,
            @RequestParam Integer progress) {

        return projectService.updateProgress(id, progress);
    }

}