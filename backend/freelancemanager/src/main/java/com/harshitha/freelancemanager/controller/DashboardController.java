package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.DashboardResponseDto;
import com.harshitha.freelancemanager.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponseDto getDashboard() {

        return dashboardService.getDashboardSummary();
    }
}