package com.example.petclinicDB.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Integer id;
    private String petName;
    private String status;
    private String breed;
    private String gender;
    private String dateOfBirth;
    private OwnerDto pawrent;
}
