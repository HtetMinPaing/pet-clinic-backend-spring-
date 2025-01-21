package com.example.petclinicDB.mapper.impl;

import com.example.petclinicDB.domain.dto.VetDto;
import com.example.petclinicDB.domain.entity.VetEntity;
import com.example.petclinicDB.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VetMapper implements Mapper<VetEntity, VetDto> {

    private ModelMapper modelMapper;

    public VetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VetEntity mapToEntity(VetDto vetDto) {
        return modelMapper.map(vetDto, VetEntity.class);
    }

    @Override
    public VetDto mapToDto(VetEntity vetEntity) {
        return modelMapper.map(vetEntity, VetDto.class);
    }
}
