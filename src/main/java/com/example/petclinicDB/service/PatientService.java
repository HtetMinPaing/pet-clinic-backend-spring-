package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PatientService {

    PatientEntity addPatient(PatientEntity patient);

    List<PatientEntity> findAll();

    Page<PatientEntity> findAll(Pageable pageable);

    PatientEntity findPatient(Integer id);

    PatientEntity updatePatient(Integer id, PatientEntity patient);

    void deletePatient(Integer id);
}
