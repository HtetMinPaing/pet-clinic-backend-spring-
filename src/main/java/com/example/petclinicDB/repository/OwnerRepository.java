package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.OwnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<OwnerEntity, Integer>, PagingAndSortingRepository<OwnerEntity, Integer> {
    Optional<OwnerEntity> findByEmail(String email);
}
