package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import com.example.petclinicDB.repository.OwnerRepository;
import com.example.petclinicDB.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerEntity registerOwner(OwnerEntity ownerEntity) {
        return ownerRepository.save(ownerEntity);
    }

    @Override
    public OwnerEntity findOwnerById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    @Override
    public OwnerEntity findOwnerByEmail(String email) {
        return ownerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }
}
