package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.entity.UserEntity;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.repository.UserRepository;
import com.example.petclinicDB.repository.PatientRepository;
import com.example.petclinicDB.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PatientEntity addPatient(PatientEntity patient) {
        UserEntity owner = userRepository.findById(patient.getPawrent().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        patient.setPawrent(owner);
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

    @Override
    public Page<PatientEntity> filterPatients(String search, String status, String breed, PageRequest pageRequest) {
        return patientRepository.findPatientsWithFilters(search, status, breed, pageRequest);
    }

    @Override
    public PatientEntity findPatient(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public PatientEntity updatePatient(Integer id, PatientEntity patient) {
        Optional<PatientEntity> foundPatient = patientRepository.findById(id);
        return foundPatient.map(existingPatient -> {
            Optional.ofNullable(patient.getPetName()).ifPresent(existingPatient::setPetName);
            Optional.ofNullable(patient.getStatus()).ifPresent(existingPatient::setStatus);
            Optional.ofNullable(patient.getPawrent()).ifPresent(existingPatient::setPawrent);
            Optional.ofNullable(patient.getBreed()).ifPresent(existingPatient::setBreed);
            Optional.ofNullable(patient.getGender()).ifPresent(existingPatient::setGender);
            Optional.ofNullable(patient.getDateOfBirth()).ifPresent(existingPatient::setDateOfBirth);
//            Optional.ofNullable(patient.getContactPhone()).ifPresent(existingPatient::setContactPhone);
//            Optional.ofNullable(patient.getAddress()).ifPresent(existingPatient::setAddress);
//            Optional.ofNullable(patient.getCity()).ifPresent(existingPatient::setCity);
//            Optional.ofNullable(patient.getTownship()).ifPresent(existingPatient::setTownship);
            patientRepository.save(existingPatient);
            return existingPatient;
        }).orElseThrow(() -> new RuntimeException("Patient cannot be update"));
    }

    @Override
    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }

}
