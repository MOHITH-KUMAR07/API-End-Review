package com.example.demo.repository;

import com.example.demo.entity.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface GardenerRepository extends JpaRepository<Gardener, Long> {

    List<Gardener> findByName(String name);

    List<Gardener> findByContact(String contact);

    int deleteByName(String name);

    @Query("SELECT g FROM Gardener g WHERE g.experience = :experience")
    List<Gardener> findByExperience(@Param("experience") String experience);

    @Query(value = "SELECT * FROM gardener WHERE LENGTH(experience) > :length", nativeQuery = true)
    List<Gardener> findByExperienceLengthGreaterThan(@Param("length") int length);
}