package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Integer>, PagingAndSortingRepository<PatientEntity, Integer> {
}
