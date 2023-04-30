package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TargetService {
    private TargetRepository targetRepository;
    private PersonService personService;

    public TargetService(TargetRepository targetRepository, PersonService personService) {
        this.targetRepository = targetRepository;
        this.personService = personService;
    }

    public List<Target> getAllTargets(){
        return (List<Target>) targetRepository.findAll();
    }

    // New methods
    public List<Target> getTargetByPersonId(Long personId) {
        return targetRepository.findByPersonId(personId);
    }

    public Target updateTarget(Long targetId, Target targetDetails) {
        Target target = targetRepository.findById(targetId).orElse(null);
        if(target == null) return null;
        target.setDailySteps(targetDetails.getDailySteps());
        target.setWeightLoss(targetDetails.getWeightLoss());
        target.setWeightLossPercentage(targetDetails.getWeightLossPercentage());
        return targetRepository.save(target);
    }

    public Target createTarget(Long personId, Target targetDetails) {
        Person person = personService.getPersonById(personId);
        if (person==null) {
            return null;
        } else {
            targetDetails.setPerson(person);
            Date currentDateTime = new Date(System.currentTimeMillis());
            targetDetails.setDateUpdated(currentDateTime);
            return targetRepository.save(targetDetails);
        }
    }

    public Target deleteTargetById(Long targetId) {
        Target target = targetRepository.findById(targetId).orElse(null);
        if(target == null) return null;
        targetRepository.delete(target);
        return target;
    }

    public Target getTargetById(Long targetId) {
        Target target = targetRepository.findById(targetId).orElse(null);
        if(target == null) return null;
        return target;
    }
}
