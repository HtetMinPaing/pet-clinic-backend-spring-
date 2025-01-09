package com.example.petclinicDB.repository;

import com.example.petclinicDB.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer>, PagingAndSortingRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    @Query(
            value = """
                SELECT * FROM users o
                WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%'))
                )
            """,
            countQuery =  """
                SELECT COUNT(*) FROM users o
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
    Page<UserEntity> findOwnersWithFilters(
            @Param("search") String search,
            @Param("city") String city,
            @Param("township") String township,
            PageRequest pageRequest
    );
}
