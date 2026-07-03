package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.DashboardResponseDto;
import com.harshitha.freelancemanager.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.harshitha.freelancemanager.dto.RevenueChartDto;
import com.harshitha.freelancemanager.service.DashboardService;

import com.harshitha.freelancemanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private JwtUtil jwtUtil;

    private Long extractUserId(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        return jwtUtil.extractUserId(token);
    }
    @GetMapping
    public DashboardResponseDto getDashboard(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return dashboardService.getDashboardSummary(userId);
    }
    @GetMapping("/revenue")
    public List<RevenueChartDto> getRevenue(HttpServletRequest request) {

        Long userId = extractUserId(request);

        return dashboardService.getMonthlyRevenue(userId);
    }
}