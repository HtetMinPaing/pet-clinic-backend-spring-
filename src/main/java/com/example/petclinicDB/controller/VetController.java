package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.VetDto;
import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.mapper.impl.VetMapper;
import com.example.petclinicDB.service.VetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vet/")
@CrossOrigin(origins = "http://localhost:3000/")
public class VetController {

    private VetService vetService;

    private VetMapper vetMapper;

    public VetController(VetService vetService, VetMapper vetMapper) {
        this.vetService = vetService;
        this.vetMapper = vetMapper;
    }

    @PostMapping(path = "register")
    public ResponseEntity<VetDto> registerVet(@RequestBody VetDto vetDto) {
        VetEntity vet = vetMapper.mapToEntity(vetDto);
        VetEntity savedVet = vetService.registerVet(vet);
        VetDto returnVet = vetMapper.mapToDto(savedVet);
        return new ResponseEntity<>(returnVet, HttpStatus.CREATED);
    }

    @GetMapping(path = "find/{1d}")
    public ResponseEntity<VetDto> findById(@PathVariable("id") Integer id) {
        VetEntity foundVet = vetService.findById(id);
        VetDto returnVet = vetMapper.mapToDto(foundVet);
        return new ResponseEntity<>(returnVet, HttpStatus.FOUND);
    }

    @GetMapping(path = "find")
    public ResponseEntity<VetDto> findByEmail(@RequestBody VetDto vetDto) {
        VetEntity foundVet = vetService.findByEmail(vetDto.getEmail());
        VetDto returnVet = vetMapper.mapToDto(foundVet);
        return new ResponseEntity<>(returnVet, HttpStatus.FOUND);
    }

    @GetMapping(path = "/all/pages")
    public Page<VetDto> findAllWithFilter(
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String township,
            @RequestParam(required = false) String email
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<VetEntity> pages = vetService.findAllWithFilters(search, city, township, email, pageRequest);
        return pages.map(vetMapper::mapToDto);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<VetDto> updateVet(
            @PathVariable Integer id,
            @RequestBody VetDto vetDto
    ) {
        VetEntity updatedVet = vetService.updateVet(id, vetDto);
        VetDto returnVet = vetMapper.mapToDto(updatedVet);
        return new ResponseEntity<>(returnVet, HttpStatus.OK);
    }

}
