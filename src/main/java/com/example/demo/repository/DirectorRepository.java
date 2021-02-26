package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Director;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{



}
