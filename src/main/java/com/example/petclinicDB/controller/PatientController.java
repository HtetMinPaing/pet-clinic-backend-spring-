package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.PatientDto;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.mapper.Mapper;
import com.example.petclinicDB.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/patient")
public class PatientController {

    private PatientService patientService;

    private Mapper<PatientEntity, PatientDto> patientMapper;

    public PatientController(PatientService patientService, Mapper<PatientEntity, PatientDto> patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @GetMapping(path = "/all")
    public List<PatientDto> findAllPatients() {
        return patientService.findAll().stream()
                .map(patientMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/all/pages")
    public Page<PatientDto> findAllPatients(Pageable pageable) {
        Page<PatientEntity> pages = patientService.findAll(pageable);
        return pages.map(patientMapper::mapFrom);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        PatientEntity patient = patientMapper.mapTo(patientDto);
        PatientEntity savedPatient = patientService.addPatient(patient);
        PatientDto returnPatient = patientMapper.mapFrom(savedPatient);
        return new ResponseEntity<>(returnPatient, HttpStatus.OK);
    }

}
