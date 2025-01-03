package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.PatientDto;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.repository.PatientRepository;
import com.example.petclinicDB.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientEntity addPatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<PatientEntity> findAll() {
        return StreamSupport.stream(
                patientRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PatientEntity> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }
}
