package com.example.petclinicDB.domain.dto;

import com.example.petclinicDB.domain.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    private String fullName;

    private String email;

    private String password;

    private String contactPhone;

    private String address;

    private String city;

    private String township;

    private List<RoleEntity> roles = new ArrayList<>();
}
