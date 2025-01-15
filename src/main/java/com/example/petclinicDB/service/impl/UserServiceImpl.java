package com.example.petclinicDB.service.impl;

import com.example.petclinicDB.domain.dto.UserDto;
import com.example.petclinicDB.domain.entity.RoleEntity;
import com.example.petclinicDB.domain.entity.UserEntity;
import com.example.petclinicDB.repository.RoleRepository;
import com.example.petclinicDB.repository.UserRepository;
import com.example.petclinicDB.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity registerOwner(UserEntity userEntity) {
        RoleEntity roles = roleRepository.findByName("OWNER").get();
        userEntity.setRoles(Collections.singletonList(roles));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findOwnerById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    @Override
    public UserEntity findOwnerByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    @Override
    public UserEntity updateOwner(Integer id, UserDto userDto) {
        Optional<UserEntity> foundowner = userRepository.findById(id);
        return foundowner.map(existingOwner -> {
            Optional.ofNullable(userDto.getFullName()).ifPresent(existingOwner::setFullName);
            Optional.ofNullable(userDto.getEmail()).ifPresent(existingOwner::setEmail);
            Optional.ofNullable(userDto.getPassword()).ifPresent(existingOwner::setPassword);
            Optional.ofNullable(userDto.getContactPhone()).ifPresent(existingOwner::setContactPhone);
            Optional.ofNullable(userDto.getCity()).ifPresent(existingOwner::setCity);
            Optional.ofNullable(userDto.getTownship()).ifPresent(existingOwner::setTownship);
            Optional.ofNullable(userDto.getAddress()).ifPresent(existingOwner::setAddress);
            userRepository.save(existingOwner);
            return existingOwner;
        }).orElseThrow(() -> new RuntimeException("Cannot update the owner"));
    }

    @Override
    public String deleteOwner(Integer id) {
        userRepository.deleteById(id);
        return "Owner successfully delete";
    }

    @Override
    public Page<UserEntity> filterOwner(String search, String city, String township, String email, PageRequest pageRequest) {
        return userRepository.findOwnersWithFilters(search, city, township, email, pageRequest);
    }

    @Override
    public String deleteSelectedId(List<Integer> ids) {
        userRepository.deleteAllById(ids);
        return "Patient successfully deleted (Id: "+ ids + ")";
    }
}
