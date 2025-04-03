package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
    Page<User> searchByUsername(@Param("username") String username, Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE LENGTH(password) > :length", nativeQuery = true)
    Page<User> findByLongPassword(@Param("length") int length, Pageable pageable);
}
