package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface OwnerService {
    OwnerEntity registerOwner(OwnerEntity ownerEntity);

    OwnerEntity findOwnerById(Integer id);

    OwnerEntity findOwnerByEmail(String email);

    OwnerEntity updateOwner(Integer id, OwnerDto ownerDto);

    String deleteOwner(Integer id);

    Page<OwnerEntity> filterOwner(String search, String city, String township, PageRequest pageRequest);
}
