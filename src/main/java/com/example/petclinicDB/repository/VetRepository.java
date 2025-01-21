package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.VetEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VetRepository extends CrudRepository<VetEntity, Integer> {
    Optional<VetEntity> findByEmail(String email);
}
