package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entities.Shopcart;

@Repository
public interface ShopcartRepository extends JpaRepository<Shopcart, Long>{

	List<Shopcart> findAllById(Long id);

}
