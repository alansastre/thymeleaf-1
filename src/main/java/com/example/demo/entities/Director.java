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

	private Integer birthDate;
	
	private Integer age;
	
	
	@ManyToMany
	@JoinTable(name = "director_movie",
	joinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	private List<Movie> movies = new ArrayList<>();
	
	
	@OneToMany(mappedBy="director")
	private List<FilmProducer> filmProducer = new ArrayList<>();


	public Director() {
		
	}
	
	
	
	public Director(Long id, String name, Integer birthDate, Integer age, List<Movie> movies,
			List<FilmProducer> filmProducer) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.age = age;
		this.movies = movies;
		this.filmProducer = filmProducer;
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


	public Integer getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
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


	public List<FilmProducer> getFilmProducer() {
		return filmProducer;
	}


	public void setFilmProducer(List<FilmProducer> filmProducer) {
		this.filmProducer = filmProducer;
	}



	@Override
	public String toString() {
		return "Director [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", age=" + age + ", movies="
				+ movies + ", filmProducer=" + filmProducer + "]";
	}
	
	
	
	
}