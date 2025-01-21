package com.example.petclinicDB.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VetDto {
    private Integer id;

    private String fullName;

    private String email;

    private String contactPhone;

    private String address;

    private String city;

    private String township;
}
