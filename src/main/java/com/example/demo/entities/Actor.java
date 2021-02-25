package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name="actors")
public class Actor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@ManyToMany
	@JoinTable(name = "actor_movie",
	joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	private List<User> movies = new ArrayList<User>();
	
}

