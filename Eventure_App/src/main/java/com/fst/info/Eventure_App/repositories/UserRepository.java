package com.fst.info.Eventure_App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fst.info.Eventure_App.models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findById(@Param("id") Long id);

}
