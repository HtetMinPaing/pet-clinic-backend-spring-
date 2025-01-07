package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.OwnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OwnerRepository extends CrudRepository<OwnerEntity, Integer>, PagingAndSortingRepository<OwnerEntity, Integer> {
}
