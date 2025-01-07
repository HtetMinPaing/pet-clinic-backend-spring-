package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;

public interface OwnerService {
    OwnerEntity registerOwner(OwnerEntity ownerEntity);
}
