package com.example.petclinicDB.service;

import com.example.petclinicDB.domain.dto.UserDto;
import com.example.petclinicDB.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    UserEntity registerOwner(UserEntity userEntity);

    UserEntity findOwnerById(Integer id);

    UserEntity findOwnerByEmail(String email);

    UserEntity updateOwner(Integer id, UserDto userDto);

    String deleteOwner(Integer id);

    Page<UserEntity> filterOwner(String search, String city, String township, String email, PageRequest pageRequest);

    String deleteSelectedId(List<Integer> ids);
}
