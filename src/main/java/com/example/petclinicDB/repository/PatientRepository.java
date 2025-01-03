package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
}
