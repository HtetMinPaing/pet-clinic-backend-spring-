package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.VetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VetRepository extends CrudRepository<VetEntity, Integer> {
    Optional<VetEntity> findByEmail(String email);

    @Query(
            value = """
                SELECT * FROM vets v
                WHERE (:city IS NULL OR LOWER(v.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(v.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(v.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(v.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(v.contact_phone) LIKE LOWER(CONCAT('%', :search, '%')))
                AND (:email IS NULL OR
                    LOWER(v.email) LIKE LOWER(CONCAT('%', :email, '%')))
            """,
            countQuery =  """
                SELECT COUNT(*) FROM vets v
                WHERE (:city IS NULL OR LOWER(v.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(v.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(v.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(v.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(v.contact_phone) LIKE LOWER(CONCAT('%', :search, '%'))
                AND (:email IS NULL OR
                    LOWER(v.email) LIKE LOWER(CONCAT('%', :email, '%')))
                )
            """,
            nativeQuery = true
    )
    Page<VetEntity> findAllWithFilters(
            @Param("search") String search,
            @Param("city") String city,
            @Param("township") String township,
            @Param("email") String email,
            PageRequest pageRequest
    );
}
