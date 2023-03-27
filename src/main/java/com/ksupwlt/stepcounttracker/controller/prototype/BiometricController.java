package com.ksupwlt.stepcounttracker.controller.prototype;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.service.BiometricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/biometrics")
public class BiometricController {
    private BiometricService biometricService;

    public BiometricController(BiometricService biometricService) {
        this.biometricService = biometricService;
    }

    @GetMapping
    public ResponseEntity<List<Biometric>> getAllBiometrics(){
        List<Biometric> biometrics = biometricService.getAllBiometrics();
        return new ResponseEntity<List<Biometric>>(biometrics, HttpStatus.OK);
    }
}
