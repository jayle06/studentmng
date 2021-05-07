package com.example.demo.repository;

import com.example.demo.model.Class;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IClassRepository extends JpaRepository<Class, Long> {

    @EntityGraph(attributePaths = "students")
    Optional<Class> findById(Long id);
}
