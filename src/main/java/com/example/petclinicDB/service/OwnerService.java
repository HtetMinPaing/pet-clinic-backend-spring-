package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.entity.OwnerEntity;

public interface OwnerService {
    OwnerEntity registerOwner(OwnerEntity ownerEntity);

    OwnerEntity findOwnerById(Integer id);

    OwnerEntity findOwnerByEmail(String email);
}
