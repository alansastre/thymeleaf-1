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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	private String title;
	
	private String sinopsis;
	
	private Integer duration;
	
	private Boolean offer;
	
	private String urlImg;
	
	private String urlTrailer;
	
	private Integer code;
	
	private Double price;
	
	@ManyToMany(mappedBy="movies")
	private List<User> users = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="id_filmproducer")
	private FilmProducer filmProducer;
	
	@ManyToMany(mappedBy="movies")
	private List<Actor> actors = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "movie_genre",
	joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private List<Genre> genres = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "movie_cinema",
	joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "cinema_id", referencedColumnName = "id"))
	private List<Cinema> cinemas = new ArrayList<>();
	
	public Movie() {
		
	}

	public Movie(String title, String sinopsis, Integer duration) {
		super();
		this.title = title;
		this.sinopsis = sinopsis;
		this.duration = duration;
	}

	
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public FilmProducer getFilmProducer() {
		return filmProducer;
	}

	public void setFilmProducer(FilmProducer filmProducer) {
		this.filmProducer = filmProducer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUrlTrailer() {
		return urlTrailer;
	}

	public void setUrlTrailer(String urlTrailer) {
		this.urlTrailer = urlTrailer;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public Boolean getOffer() {
		return offer;
	}

	public void setOffer(Boolean offer) {
		this.offer = offer;
	}

	public String getSinopsis() {
		return sinopsis;
	}



	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", duration=" + duration + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	 

}