package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import com.example.petclinicDB.repository.OwnerRepository;
import com.example.petclinicDB.service.OwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public OwnerEntity updateOwner(Integer id, OwnerDto ownerDto) {
        Optional<OwnerEntity> foundowner = ownerRepository.findById(id);
        return foundowner.map(existingOwner -> {
            Optional.ofNullable(ownerDto.getFullName()).ifPresent(existingOwner::setFullName);
            Optional.ofNullable(ownerDto.getEmail()).ifPresent(existingOwner::setEmail);
            Optional.ofNullable(ownerDto.getPassword()).ifPresent(existingOwner::setPassword);
            Optional.ofNullable(ownerDto.getContactPhone()).ifPresent(existingOwner::setContactPhone);
            Optional.ofNullable(ownerDto.getCity()).ifPresent(existingOwner::setCity);
            Optional.ofNullable(ownerDto.getTownship()).ifPresent(existingOwner::setTownship);
            Optional.ofNullable(ownerDto.getAddress()).ifPresent(existingOwner::setAddress);
            ownerRepository.save(existingOwner);
            return existingOwner;
        }).orElseThrow(() -> new RuntimeException("Cannot update the owner"));
    }

    @Override
    public String deleteOwner(Integer id) {
        ownerRepository.deleteById(id);
        return "Owner successfully delete";
    }

    @Override
    public Page<OwnerEntity> filterOwner(String search, String city, String township, PageRequest pageRequest) {
        return ownerRepository.findOwnersWithFilters(search, city, township, pageRequest);
    }
}
