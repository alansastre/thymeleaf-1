package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.services.MovieSrviceAPI;

@Service
public class MovieServiceImp implements MovieSrviceAPI {

	@Autowired
	private MovieRepository movieRepository;
	
	
	@Override
	public Page<Movie> gettAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	
	
}
