package com.example.petclinicDB.mapper.impl;

import com.example.petclinicDB.domain.dto.PatientDto;
import com.example.petclinicDB.domain.entity.PatientEntity;
import com.example.petclinicDB.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements Mapper<PatientEntity, PatientDto> {

    private ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientEntity mapTo(PatientDto patientDto) {
        return modelMapper.map(patientDto, PatientEntity.class);
    }

    @Override
    public PatientDto mapFrom(PatientEntity patientEntity) {
        return modelMapper.map(patientEntity, PatientDto.class);
    }
}
