package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Director;
import com.example.demo.entities.FilmProducer;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import com.example.demo.entities.Shopcart;
import com.example.demo.entities.User;
import com.example.demo.repository.DirectorRepository;
import com.example.demo.repository.FilmProducerRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShopcartRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class Thymeleaf1Application implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShopcartRepository shopcartRepository;
	@Autowired
	private FilmProducerRepository filmProducerRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private DirectorRepository directorRepository;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Thymeleaf1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Genre accion = new Genre("Acción");
		Genre ciencia = new Genre("Ciencia");
		Genre ficcion = new Genre("Ficción");
		Genre comedia = new Genre("Comedia");
		Genre crimen = new Genre("Crimen");
		Genre terror = new Genre("Terror");
		genreRepository.saveAll(Arrays.asList(accion, ciencia, ficcion, comedia, crimen, terror));
		
		FilmProducer hollywood = new FilmProducer("Hollywood", 1910);
		FilmProducer warner = new FilmProducer("Warner Bros", 1890);
		FilmProducer paramount = new FilmProducer("Paramount Comedies", 1930);
		filmProducerRepository.save(hollywood);
		filmProducerRepository.save(warner);
		filmProducerRepository.save(paramount);
		
		Director director1 = new Director("Director1", "director1@com", 45);
		Director director2 = new Director("Director2", "director2@com", 56);
		Director director3 = new Director("Director3", "director3@com", 66);
		Director director4 = new Director("Director4", "director4@com", 32);
		
		directorRepository.save(director1);
		directorRepository.save(director2);
		directorRepository.save(director3);
		directorRepository.save(director4);
	
		
		Movie movie1 = new Movie("Los vengadores", "La ciudad está en peligro y los ....", 160);
		movie1.setOffer(true);
		movie1.setUrlImg("vengadores.png");
		movie1.setUrlTrailer("trailer.mp4");
		movie1.setCode(44034);
		movie1.setPrice(3.99);
		movie1.setFilmProducer(paramount);
		movie1.getGenres().addAll(Arrays.asList(accion, ciencia));
		movie1.setDirector(director1);
		
		Movie movie2 = new Movie("Los extraordinarios", "La ciudad está en peligro y los ....", 120);
		movie2.setCode(55034);
		movie2.setPrice(45.9);
		movie2.setOffer(true);
		movie2.setFilmProducer(paramount);
		movie2.getGenres().addAll(Arrays.asList(crimen, terror));
		movie2.setDirector(director2);
		
		Movie movie3 = new Movie("Los developers", "El código está en peligro y los ....", 210);
		movie3.setCode(66034);
		movie3.setPrice(12.2);
		movie3.setOffer(true);
		movie3.setFilmProducer(hollywood);
		movie3.getGenres().addAll(Arrays.asList(comedia, ficcion));
		movie3.setDirector(director3);
		
		List<Movie> movies = Arrays.asList(movie1, movie2, movie3);
		movieRepository.saveAll(movies);
		
		User user1 = new User("Juan", "juan@example.com", 33, true, "admin", movies);
		userRepository.save(user1);
		
		Movie movie4 = new Movie("Pelicula 4", "La ciudad está en peligro y los ....", 120);
		movie4.setPrice(23.3);
		movie4.setOffer(true);
		movie4.setFilmProducer(warner);
		movie4.getGenres().addAll(Arrays.asList(ciencia));
		movie4.setDirector(director4);
		movieRepository.save(movie4);

		Movie movie5 = new Movie("Pelicula 5", "La ciudad está en peligro y los ....", 120);
		movie5.setPrice(88.7);
		movie5.setOffer(false);
		movie5.setFilmProducer(warner);
		movie5.setDirector(director1);
		movieRepository.save(movie5);

		User user2 = new User("Ayman", "ayman@example.com", 33, true, "admin");
		userRepository.save(user2);
		
		Shopcart carrito1 = new Shopcart();
		carrito1.setMovies(Arrays.asList(movie4, movie5));
		carrito1.setUser(user2);
		shopcartRepository.save(carrito1);
		


		
	}

}
