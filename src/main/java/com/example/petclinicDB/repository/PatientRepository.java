package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends CrudRepository<PatientEntity, Integer>, PagingAndSortingRepository<PatientEntity, Integer> {
    @Query(value = """
                SELECT p.*
                FROM patients p
                LEFT JOIN owners o ON p.pawrent_id = o.id
                WHERE (:search IS NULL OR
                       LOWER(p.pet_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(p.breed) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.city) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.township) LIKE LOWER(CONCAT('%', :search, '%')))
                  AND (:status IS NULL OR LOWER(p.status) = LOWER(:status))
                  AND (:breed IS NULL OR LOWER(p.breed) = LOWER(:breed))
                ORDER BY p.id
            """,
            countQuery = """
                SELECT COUNT(*)
                FROM patients p
                LEFT JOIN owners o ON p.pawrent_id = o.id
                WHERE (:search IS NULL OR
                       LOWER(p.pet_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(p.breed) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.city) LIKE LOWER(CONCAT('%', :search, '%')) OR
                       LOWER(o.township) LIKE LOWER(CONCAT('%', :search, '%')))
                AND (:status IS NULL OR LOWER(p.status) = LOWER(:status))
                AND (:breed IS NULL OR LOWER(p.breed) = LOWER(:breed))
            """,
            nativeQuery = true)
    Page<PatientEntity> findPatientsWithFilters(
            @Param("search") String search,
            @Param("status") String status,
            @Param("breed") String breed,
            Pageable pageable
    );

    @Query(value = """
    SELECT * FROM patients
    WHERE (:search IS NULL OR
           LOWER(pet_name) LIKE LOWER(CONCAT('%', :search, '%')))
""", nativeQuery = true)
    Page<PatientEntity> findPatientsWithNativeQuery(@Param("search") String search, Pageable pageable);
}
