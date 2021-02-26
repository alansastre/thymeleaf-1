package com.example.demo.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorias-Cine")
public class CategoryCinema  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	
	@ManyToMany(mappedBy="categories")
	private List<Cinema> cinema = new ArrayList<>();



	public CategoryCinema(String name) {
		super();
		this.name = name;
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



	public List<Cinema> getCinema() {
		return cinema;
	}



	public void setCinema(List<Cinema> cinema) {
		this.cinema = cinema;
	}



	@Override
	public String toString() {
		return "CategoryCinema [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
