package com.example.petclinicDB.mapper.impl;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import com.example.petclinicDB.mapper.Mapper;
import org.modelmapper.ModelMapper;

public class OwnerMapper implements Mapper<OwnerEntity, OwnerDto> {

    private ModelMapper modelMapper;

    public OwnerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerEntity mapToEntity(OwnerDto ownerDto) {
        return modelMapper.map(ownerDto, OwnerEntity.class);
    }

    @Override
    public OwnerDto mapToDto(OwnerEntity ownerEntity) {
        return modelMapper.map(ownerEntity, OwnerDto.class);
    }
}
