package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.VetDto;
import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.repository.VetRepository;
import com.example.petclinicDB.service.VetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
            Optional.ofNullable(vetDto.getFullName()).ifPresent(existingVet::setFullName);
            Optional.ofNullable(vetDto.getEmail()).ifPresent(existingVet::setEmail);
            Optional.ofNullable(vetDto.getContactPhone()).ifPresent(existingVet::setContactPhone);
            Optional.ofNullable(vetDto.getCity()).ifPresent(existingVet::setCity);
            Optional.ofNullable(vetDto.getTownship()).ifPresent(existingVet::setTownship);
            Optional.ofNullable(vetDto.getAddress()).ifPresent(existingVet::setAddress);
            vetRepository.save(existingVet);
            return existingVet;
        }).orElseThrow(() -> new RuntimeException("Vet updated successfully"));
    }

    @Override
    public String deleteOne(Integer id) {
        vetRepository.deleteById(id);
        return "Delete Successfully Vet Id: " + id;
    }

    @Override
    public String deleteSelected(List<Integer> ids) {
        vetRepository.deleteAllById(ids);
        return "Delete Successfully Selected Ids";
    }
}
