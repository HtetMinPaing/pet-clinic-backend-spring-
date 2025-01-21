package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.VetDto;
import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.repository.VetRepository;
import com.example.petclinicDB.service.VetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VetServiceImpl implements VetService {

    private VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public VetEntity registerVet(VetEntity vet) {
        return vetRepository.save(vet);
    }

    @Override
    public VetEntity findById(Integer id) {
        return vetRepository.findById(id).orElseThrow(() -> new RuntimeException("Vet not found"));
    }

    @Override
    public VetEntity findByEmail(String email) {
        return vetRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Vet not found"));
    }

    @Override
    public Page<VetEntity> findAllWithFilters(String search, String city, String township, String email, PageRequest pageRequest) {
        return vetRepository.findAllWithFilters(search, city, township, email, pageRequest);
    }

    @Override
    public VetEntity updateVet(Integer id, VetDto vetDto) {
        Optional<VetEntity> foundVet = vetRepository.findById(id);
        return foundVet.map(existingVet -> {
            Optional.ofNullable(vetDto.getFullName()).ifPresent(vetDto::setFullName);
            Optional.ofNullable(vetDto.getEmail()).ifPresent(vetDto::setEmail);
            Optional.ofNullable(vetDto.getContactPhone()).ifPresent(vetDto::setContactPhone);
            Optional.ofNullable(vetDto.getCity()).ifPresent(vetDto::setCity);
            Optional.ofNullable(vetDto.getTownship()).ifPresent(vetDto::setTownship);
            Optional.ofNullable(vetDto.getAddress()).ifPresent(vetDto::setAddress);
            vetRepository.save(existingVet);
            return existingVet;
        }).orElseThrow(() -> new RuntimeException("Vet updated successfully"));
    }
}
