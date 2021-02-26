package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="director")
public class Director implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String email;
	
	private Integer age;
	
	
	@OneToMany(mappedBy="director")
	private List<Movie> movies = new ArrayList<Movie>();
	
	
	public Director() {}
	
	


	public Director(String name, String email, Integer age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}


	@Override
	public String toString() {
		return "Director [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + "]";
	}


	
	
	
	
	
	
	
	
}