package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.dto.OwnerDto;
import com.example.petclinicDB.domain.entity.OwnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<OwnerEntity, Integer>, PagingAndSortingRepository<OwnerEntity, Integer> {
    Optional<OwnerEntity> findByEmail(String email);

    @Query(
            value = """
                SELECT * FROM owner o
                WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%'))
                )
            """,
            countQuery =  """
                SELECT COUNT(*) FROM owner o
                WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%'))
                )
            """,
            nativeQuery = true
    )
    Page<OwnerEntity> findOwnersWithFilters(
            @Param("search") String search,
            @Param("city") String city,
            @Param("township") String township,
            PageRequest pageRequest
    );
}
