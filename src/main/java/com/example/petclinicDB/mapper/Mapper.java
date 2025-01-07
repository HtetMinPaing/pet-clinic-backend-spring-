package com.example.petclinicDB.mapper;

public interface Mapper <A, B>{
    A mapToEntity(B b);

    B mapToDto(A a);
}
