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
                    LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%')))
                AND (:email IS NULL OR
                    LOWER(o.email) LIKE LOWER(CONCAT('%', :email, '%')))
            """,
            countQuery =  """
                SELECT COUNT(*) FROM users o
                WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
                AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
                AND (:search IS NULL OR
                    LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
                    LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%'))
                AND (:email IS NULL OR
                    LOWER(o.email) LIKE LOWER(CONCAT('%', :email, '%')))
                )
            """,
            nativeQuery = true
    )
    Page<UserEntity> findOwnersWithFilters(
            @Param("search") String search,
            @Param("city") String city,
            @Param("township") String township,
            @Param("email") String email,
            PageRequest pageRequest
    );

//    @Query(
//            value = """
//        SELECT DISTINCT o.*
//        FROM users o
//        JOIN user_roles ur ON o.id = ur.user_id
//        JOIN roles r ON ur.role_id = r.id
//        WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
//        AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
//        AND (:search IS NULL OR
//            LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
//            LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
//            LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%')))
//        AND (:role IS NULL OR LOWER(r.name) = LOWER(:role))
//    """,
//            countQuery = """
//        SELECT COUNT(DISTINCT o.id)
//        FROM users o
//        JOIN user_roles ur ON o.id = ur.user_id
//        JOIN roles r ON ur.role_id = r.id
//        WHERE (:city IS NULL OR LOWER(o.city) LIKE LOWER(:city))
//        AND (:township IS NULL OR LOWER(o.township) LIKE LOWER(:township))
//        AND (:search IS NULL OR
//            LOWER(o.full_name) LIKE LOWER(CONCAT('%', :search, '%')) OR
//            LOWER(o.address) LIKE LOWER(CONCAT('%', :search, '%')) OR
//            LOWER(o.contact_phone) LIKE LOWER(CONCAT('%', :search, '%')))
//        AND (:role IS NULL OR LOWER(r.name) = LOWER(:role))
//    """,
//            nativeQuery = true
//    )
//    Page<UserEntity> findOwnersWithFilters(
//            @Param("search") String search,
//            @Param("city") String city,
//            @Param("township") String township,
//            @Param("role") String role,
//            Pageable pageable
//    );

}
