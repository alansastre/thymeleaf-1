package com.example.demo.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.entities.Movie;
import com.example.demo.repository.MovieRepository;

public interface MovieSrviceAPI {

	Page<Movie> gettAll (Pageable pageable);
	
}
