package com.ksupwlt.stepcounttracker.repository;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByPersonId(Long personId);
}