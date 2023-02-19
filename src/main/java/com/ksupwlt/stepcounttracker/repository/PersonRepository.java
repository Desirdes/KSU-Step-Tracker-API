package com.ksupwlt.stepcounttracker.repository;

import com.ksupwlt.stepcounttracker.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
