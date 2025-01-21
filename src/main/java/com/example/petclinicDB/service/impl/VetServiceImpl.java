package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.repository.VetRepository;
import com.example.petclinicDB.service.VetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public VetEntity findByEmail(String email) {
        return vetRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Vet not found"));
    }

    @Override
    public Page<VetEntity> findAllWithFilters(String search, String city, String township, String email, PageRequest pageRequest) {
        return vetRepository.findAllWithFilters(search, city, township, email, pageRequest);
    }
}
