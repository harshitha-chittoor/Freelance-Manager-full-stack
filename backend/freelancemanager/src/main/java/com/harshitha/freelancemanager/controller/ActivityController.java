package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.entity.Activity;
import com.harshitha.freelancemanager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:5173")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/recent")
    public List<Activity> getRecentActivities() {

        return activityService.getRecentActivities();

    }
}