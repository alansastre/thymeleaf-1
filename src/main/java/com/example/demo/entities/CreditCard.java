package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="creditcard")
public class CreditCard implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="ccv")
	private String ccv;
	
	@Column(name="number")
	private String number;
	
	@Column(name="name")
	private String name;
	
	
	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getccv() {
		return ccv;
	}

	public void setccv(String ccv) {
		this.ccv = ccv;
	}

	public String getnumber() {
		return number;
	}

	public void setnumber(String number) {
		this.number = number;
	}

	public String getname() {
		return name;
	}

	public void setCity(String name) {
		this.name = name;
	}

	
	// Constructors
	public CreditCard() {}
	public CreditCard(String ccv, String number, String name) {
		this.ccv = ccv;
		this.number = number;
		this.name = name;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", ccv=" + ccv + ", number=" + number + ", name=" + name + "]";
	}
		
}