package com.ksupwlt.stepcounttracker.repository;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiometricRepository extends JpaRepository<Biometric, Long> {
    List<Biometric> findByPersonId(Long personId);
}