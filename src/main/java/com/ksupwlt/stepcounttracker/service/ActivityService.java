package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities(){
        return (List<Activity>) activityRepository.findAll();
    }
}
