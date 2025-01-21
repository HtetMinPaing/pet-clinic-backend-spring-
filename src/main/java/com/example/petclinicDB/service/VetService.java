package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.entity.VetEntity;

public interface VetService {
    VetEntity registerVet(VetEntity vet);

    VetEntity findById(Integer id);
}
