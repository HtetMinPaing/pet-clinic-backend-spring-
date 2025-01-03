package com.example.petclinicDB.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String petName;
    private String status;
    private String pawrent;
    private String breed;
    private String gender;
    private String dateOfBirth;
    private String contactPhone;
    private String address;
    private String city;
    private String township;
}
