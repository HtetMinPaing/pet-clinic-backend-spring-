package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import com.example.petclinicDB.mapper.impl.OwnerMapper;
import com.example.petclinicDB.service.OwnerService;
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

}
