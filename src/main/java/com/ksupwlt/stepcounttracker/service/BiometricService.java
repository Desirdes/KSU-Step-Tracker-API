package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.repository.BiometricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiometricService {
    private BiometricRepository biometricRepository;
    private PersonService personService;

    public BiometricService(BiometricRepository biometricRepository, PersonService personService) {
        this.biometricRepository = biometricRepository;
        this.personService = personService;
    }

    public List<Biometric> getAllBiometrics(){
        return (List<Biometric>) biometricRepository.findAll();
    }

    //New code
    public List<Biometric> getBiometricByPersonId(Long personId) {
        return biometricRepository.findByPersonId(personId);
                //.orElseThrow(() -> new ResourceNotFoundException("Biometric", "personId", personId));
    }

    public Biometric updateBiometric(Long biometricId, Biometric biometricDetails) {
        Biometric biometric = biometricRepository.findById(biometricId).orElse(null);
        if(biometric == null) return null;
        biometric.setHeight(biometricDetails.getHeight());
        biometric.setWeight(biometricDetails.getWeight());
        biometric.setWaistCircumference(biometricDetails.getWaistCircumference());
        biometric.setNeckCircumference(biometricDetails.getNeckCircumference());
        biometric.setBodyFatPercentage(biometricDetails.getBodyFatPercentage());
        return biometricRepository.save(biometric);
    }

    public Biometric createBiometric(Long personId, Biometric biometricDetails) {
        Person person = personService.getPersonById(personId);
        if (person==null) return null;
        biometricDetails.setPerson(person);
        return biometricRepository.save(biometricDetails);
    }

    public Biometric deleteBiometricById(Long biometricId) {
        Biometric biometric = biometricRepository.findById(biometricId).orElse(null);
        if(biometric == null) return null;
        biometricRepository.delete(biometric);
        return biometric;
    }

    public Biometric getBiometricById(Long biometricId) {
        Biometric biometric = biometricRepository.findById(biometricId).orElse(null);
        if(biometric == null) return null;
        return biometric;
    }
}
