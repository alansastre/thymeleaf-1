package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.CreditCard;

public interface CreditCardRepository extends JpaRepository <CreditCard, String>{


}