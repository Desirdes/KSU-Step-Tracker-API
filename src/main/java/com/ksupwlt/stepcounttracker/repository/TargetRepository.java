package com.ksupwlt.stepcounttracker.repository;

import com.ksupwlt.stepcounttracker.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
}