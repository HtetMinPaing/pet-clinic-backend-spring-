package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import com.example.petclinicDB.mapper.impl.OwnerMapper;
import com.example.petclinicDB.service.OwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/owner/")
public class OwnerController {

    private OwnerService ownerService;

    private OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @PostMapping(path = "register")
    public ResponseEntity<OwnerDto> registerOwner(@RequestBody OwnerDto ownerDto) {
        OwnerEntity owner = ownerMapper.mapToEntity(ownerDto);
        OwnerEntity savedOwner = ownerService.registerOwner(owner);
        OwnerDto returnOwner = ownerMapper.mapToDto(savedOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.CREATED);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<OwnerDto> findOwner(@PathVariable("id") Integer id) {
        OwnerEntity foundOwner = ownerService.findOwnerById(id);
        OwnerDto returnOwner = ownerMapper.mapToDto(foundOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<OwnerDto> findOwnerByEmail(@RequestBody OwnerDto ownerDto) {
        OwnerEntity foundOwner = ownerService.findOwnerByEmail(ownerDto.getEmail());
        OwnerDto returnOwner = ownerMapper.mapToDto(foundOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @GetMapping(path = "/all/pages")
    public Page<OwnerDto> findAllPatients(
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String township
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<OwnerEntity> pages = ownerService.filterOwner(search, city, township, pageRequest);
        return pages.map(ownerMapper::mapToDto);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<OwnerDto> updateOwner(
            @PathVariable Integer id,
            @RequestBody OwnerDto ownerDto
    ) {
        OwnerEntity updatedOwner = ownerService.updateOwner(id, ownerDto);
        OwnerDto returnOwner = ownerMapper.mapToDto(updatedOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Integer id) {
        String response = ownerService.deleteOwner(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
