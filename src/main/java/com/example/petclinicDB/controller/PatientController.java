package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.PatientDto;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.mapper.Mapper;
import com.example.petclinicDB.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/patient")
@CrossOrigin(origins = "http://localhost:3000/")
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
                .map(patientMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/all/pages")
    public Page<PatientDto> findAllPatients(
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String breed
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page,size, sort);
        Page<PatientEntity> pages = patientService.filterPatients(search, status, breed, pageRequest);
        return pages.map(patientMapper::mapToDto);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<PatientDto> findPatient(@PathVariable("id") Integer id) {
        PatientEntity foundPatient = patientService.findPatient(id);
        PatientDto returnPatient = patientMapper.mapToDto(foundPatient);
        return new ResponseEntity<>(returnPatient, HttpStatus.FOUND);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        PatientEntity patient = patientMapper.mapToEntity(patientDto);
        PatientEntity savedPatient = patientService.addPatient(patient);
        PatientDto returnPatient = patientMapper.mapToDto(savedPatient);
        return new ResponseEntity<>(returnPatient, HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable("id") Integer id,
            @RequestBody PatientDto patientDto
    ) {
        PatientEntity patient = patientMapper.mapToEntity(patientDto);
        PatientEntity savedPatient = patientService.updatePatient(id, patient);
        PatientDto returnPatient = patientMapper.mapToDto(savedPatient);
        return new ResponseEntity<>(returnPatient, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient successfully deleted (Id: "+ id + ")",HttpStatus.OK);
    }

}
