package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.repository.VetRepository;
import com.example.petclinicDB.service.VetService;
import org.springframework.stereotype.Service;

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
}
