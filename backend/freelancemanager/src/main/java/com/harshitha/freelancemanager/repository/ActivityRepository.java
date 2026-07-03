package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findTop5ByOrderByCreatedAtDesc();

}