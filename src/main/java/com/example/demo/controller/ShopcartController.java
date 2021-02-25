package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Movie;
import com.example.demo.entities.Shopcart;
import com.example.demo.entities.User;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShopcartRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class ShopcartController {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ShopcartRepository shopcartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	// ADD MOVIE TO CURRENT SHOPCART IN SESSION
	@GetMapping("/shopcarts/{id}/addmovie")
	public String addToShopCart(@PathVariable Long id, Model model, HttpSession session) {
		// Check if there is shopcart in current session
		Shopcart shopcart = (Shopcart) session.getAttribute("shopcart");
		if (shopcart == null) {
			// is is null it means it's the first time that user click on add movie
			shopcart = new Shopcart();
		}
		
		//  Find movie in database
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if (movieOpt.isPresent()) { // If movie exists then add it to shopcart
			Movie movie = movieOpt.get();
			// If shopcart doesn't contains movie then add it
			if (!shopcart.getMovies().contains(movie)) {
				shopcart.getMovies().add(movie);
			}
		}
		// save shopcart in session
		session.setAttribute("shopcart", shopcart);
		// save in model number of movies in shopcart
		model.addAttribute("shopcart_items", shopcart.getMovies().size());
		model.addAttribute("movies", movieRepository.findAll());
		return "movie-list";
	}
	
	// DELETE MOVIE FROM CURRENT SHOPCART IN SESSION
	@GetMapping("/shopcarts/{id}/deletemovie")
	public String deleteToShopCart(@PathVariable Long id, Model model, HttpSession session) {
		// Check if there is shopcart in current session
		Shopcart shopcart = (Shopcart) session.getAttribute("shopcart");
		if (shopcart == null) 
			return "redirect:/movies";

		//  Find movie in database
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if (movieOpt.isPresent()) { // If movie exists then add it to shopcart
			Movie movie = movieOpt.get();
			// If shopcart doesn't contains movie then add it
			if (shopcart.getMovies().contains(movie)) {
				shopcart.getMovies().remove(movie);
			}
		}
		// save shopcart in session
		session.setAttribute("shopcart", shopcart);
		// save in model number of movies in shopcart
		model.addAttribute("totalPrice", calculateTotalPrice(shopcart));
		model.addAttribute("shopcart_items", shopcart.getMovies().size());
		model.addAttribute("shopcart", shopcart);
		model.addAttribute("movies", movieRepository.findAll());
		return "shopcart";
	}
	
	// SEE CURRENT SHOPCART IN SESSION
	@GetMapping("/shopcart")
	public String seeShopcart(Model model, HttpSession session) {
		// Check if there is shopcart in current session
		Shopcart shopcart = (Shopcart) session.getAttribute("shopcart");
		if (shopcart == null) {
			// is is null it means it's the first time that user click on add movie
			shopcart = new Shopcart();
		}
		model.addAttribute("totalPrice", calculateTotalPrice(shopcart));
		model.addAttribute("shopcart_items", shopcart.getMovies().size());
		model.addAttribute("shopcart", shopcart);
		return "shopcart";
	}
	private double calculateTotalPrice(Shopcart shopcart) {
		if (shopcart == null || shopcart.getMovies() == null) {
			return 0;
		}
		double totalPrice = 0;
		for (Movie movie : shopcart.getMovies()) {
			if (movie.getPrice() != null)	
				totalPrice += movie.getPrice();
		}
		return totalPrice;
	}
	
	// FINALIZE BUY
	@GetMapping("/shopcarts/checkout")
	public String checkout(Model model, HttpSession session) {
		// Get user and shopcart from session
		Shopcart shopcart = (Shopcart) session.getAttribute("shopcart");
		User user = (User) session.getAttribute("user");
		
		// if thereisnt user or session go out to movies list
		if (shopcart == null || user == null) 
			return "redirect:/movies";
		
		// retrieve user from DB
		Optional<User> userDBOpt = userRepository.findById(user.getId());
		if (!userDBOpt.isPresent()) // IF user doesnt exist then go out to movies list
			return "redirect:/movies";
		
		// set user in shopcart
		shopcart.setUser(userDBOpt.get());
		// save shopcart in DB
		shopcartRepository.save(shopcart);
		// remove shopcart from session
		session.removeAttribute("shopcart");
		return "shopcart-checkout";
	}
}
