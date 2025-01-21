package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.VetEntity;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<VetEntity, Integer> {
}
