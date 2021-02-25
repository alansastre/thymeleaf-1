package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="direcciones")
public class Direction implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="street")
	private String street;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@OneToOne(mappedBy="direction")
	private User user;
	
	
	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	// Constructors
	public Direction() {}
	public Direction(String street, String postalCode, String city, String country) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Direction [id=" + id + ", street=" + street + ", postalCode=" + postalCode + ", city=" + city
				+ ", country=" + country + "]";
	}
	
}
