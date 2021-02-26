package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Movie;
import com.example.demo.entities.User;
import com.example.demo.repository.FilmProducerRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.services.MovieSrviceAPI;


@Controller
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private FilmProducerRepository filmProducerRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	@GetMapping({"/movies", "/"})
	public String findMovies(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null)
			model.addAttribute("user", user);
		model.addAttribute("movies", movieRepository.findAll());
		return "movie-list";
	}
	
	@GetMapping("/usermovies")
	public String findUserMovies(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null)
			model.addAttribute("user", user);
		model.addAttribute("movies", movieRepository.findAllByUsersId(user.getId()));
		return "movie-list";
	}
	
	@GetMapping("/movies/{id}/view")
	public String viewMovie(@PathVariable Long id, Model model) {
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if (!movieOpt.isPresent()) {
			model.addAttribute("error", "ID movie not found.");
			model.addAttribute("movies", movieRepository.findAll());
			return "movie-list";
		}
		model.addAttribute("movie", movieOpt.get());
		return "movie-view";
	}
	
	@GetMapping("/movies/new")
	public String newMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("filmProducers", filmProducerRepository.findAll());
		model.addAttribute("genresDBList", genreRepository.findAll());
		return "movie-edit";
		
	}
	
	@GetMapping("/movies/{id}/edit")
	public String editMovie(@PathVariable Long id, Model model) {
		model.addAttribute("movie", movieRepository.findById(id).get());
		model.addAttribute("filmProducers", filmProducerRepository.findAll());
		model.addAttribute("genresDBList", genreRepository.findAll());

		return "movie-edit";
		
	}
	
	@PostMapping("/movies")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {
		System.out.println(movie);
		movieRepository.save(movie);
		// Si queremos operar con el FilmProducer asociado a esta movie lo podemos obtener con getter:
		// movie.getFilmProducer().getYear()
		return "redirect:/movies";
	}
	
	@GetMapping("/movies/{id}/delete")
	public String deleteMovie(@PathVariable Long id) {
		movieRepository.deleteById(id);
		return "redirect:/movies";
	}
	
	@GetMapping("/movies/delete")
	public String deleteMovies() {
		movieRepository.deleteAll();
		return "redirect:/movies";
	}
	
	@GetMapping("/movies/{code}/search")
	public String searchMovieByCode(@PathVariable Integer code, Model model) {
		model.addAttribute("movies", movieRepository.findAllByCode(code));
		return "movie-list";
		
	}
	
	
	@Autowired
	private MovieSrviceAPI movieServiceAPI;
	
	@GetMapping(value = "/moviepaginada")
	public String finnAll (@RequestParam Map <String, Object> params, Model model){
		int page = params.get("page") !=null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 2);
		Page<Movie> pageMovies = movieServiceAPI.gettAll(pageRequest);
		
		int totalPage =  pageMovies.getTotalPages();
		if (totalPage >0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());		
			model.addAttribute("pages", pages);		
		}
		model.addAttribute("list", pageMovies.getContent());

		return "moviepaginada";
	}
	
	
}
