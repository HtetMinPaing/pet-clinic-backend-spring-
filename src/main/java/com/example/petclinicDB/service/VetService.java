package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.dto.VetDto;
import com.example.petclinicDB.domain.entity.VetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface VetService {
    VetEntity registerVet(VetEntity vet);

    VetEntity findById(Integer id);

    VetEntity findByEmail(String email);

    Page<VetEntity> findAllWithFilters(String search, String city, String township, String email, PageRequest pageRequest);

    VetEntity updateVet(Integer id, VetDto vetDto);

    String deleteOne(Integer id);

    String deleteSelected(List<Integer> ids);
}
