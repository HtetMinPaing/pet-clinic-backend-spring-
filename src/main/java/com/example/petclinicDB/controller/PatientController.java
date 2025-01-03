package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.PatientDto;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.mapper.Mapper;
import com.example.petclinicDB.mapper.impl.PatientMapper;
import com.example.petclinicDB.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/patient")
public class PatientController {

    private PatientService patientService;

    private Mapper<PatientEntity, PatientDto> patientMapper;

    public PatientController(PatientService patientService, Mapper<PatientEntity, PatientDto> patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        PatientEntity patient = patientMapper.mapTo(patientDto);
        PatientEntity savedPatient = patientService.addPatient(patient);
        PatientDto returnPatient = patientMapper.mapFrom(savedPatient);
        return new ResponseEntity<>(returnPatient, HttpStatus.OK);
    }

}
