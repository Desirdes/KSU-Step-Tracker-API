package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService {
    private TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<Target> getAllTargets(){
        return (List<Target>) targetRepository.findAll();
    }
}
