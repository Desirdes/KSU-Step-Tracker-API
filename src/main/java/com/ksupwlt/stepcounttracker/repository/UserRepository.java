package com.ksupwlt.stepcounttracker.repository;

import com.ksupwlt.stepcounttracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}