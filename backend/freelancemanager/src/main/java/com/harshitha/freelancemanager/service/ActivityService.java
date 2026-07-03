package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.entity.Activity;
import com.harshitha.freelancemanager.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    // Save a new activity
    public void logActivity(String message, String type) {

        Activity activity = new Activity(message, type);

        activityRepository.save(activity);
    }

    // Fetch latest 5 activities
    public List<Activity> getRecentActivities() {

        return activityRepository.findTop5ByOrderByCreatedAtDesc();
    }
}