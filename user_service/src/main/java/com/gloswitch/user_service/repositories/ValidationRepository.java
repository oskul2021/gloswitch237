package com.gloswitch.user_service.repositories;

import com.gloswitch.user_service.models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    @Query("select v from Validation v where v.code = :code")
    Optional<Validation> searchCode(@Param("code") String code);
}