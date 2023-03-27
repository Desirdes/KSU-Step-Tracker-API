package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.repository.ActivityRepository;
import com.ksupwlt.stepcounttracker.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;

    private PersonService personService;

    public ActivityService(ActivityRepository activityRepository, PersonService personService) {
        this.activityRepository = activityRepository;
        this.personService = personService;
    }

    public List<Activity> getAllActivities(){
        return (List<Activity>) activityRepository.findAll();
    }

    //New code

    public Activity addActivityForPerson(Long personId, Activity activityDetails) {
        // Set the person ID for the activity
        Person person = personService.getPersonById(personId);
        if (person==null) return null;
        activityDetails.setPerson(person);
        return activityRepository.save(activityDetails);
    }

    public List<Activity> getAllActivitiesForPerson(Long personId) {
        return activityRepository.findByPersonId(personId);
    }
    public Activity updateActivityById(Long activityId, Activity activityDetails) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity==null) return null;
        activity.setDate(activityDetails.getDate());
        activity.setSteps(activityDetails.getSteps());
        return activityRepository.save(activity);
    }

    public Activity deleteActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if(activity == null) return null;
        activityRepository.delete(activity);
        return activity;
    }

    public Activity getActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if(activity == null) return null;
        return activity;
    }

    // Getting the latest activity
    public Activity getLatestActivity(Long personId){
        Activity latestActivity = activityRepository.findFirstByPersonIdOrderByDateDesc(personId);
        if(latestActivity == null) return null;
        return latestActivity;
    }
}
