package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.repository.PatientRepository;
import com.example.petclinicDB.service.PatientService;
import org.springframework.stereotype.Service;

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
}
