package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.repository.BiometricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiometricService {
    private BiometricRepository biometricRepository;

    public BiometricService(BiometricRepository biometricRepository) {
        this.biometricRepository = biometricRepository;
    }

    public List<Biometric> getAllBiometrics(){
        return (List<Biometric>) biometricRepository.findAll();
    }
}
