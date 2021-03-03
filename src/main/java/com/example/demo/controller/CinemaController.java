package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Cinema;
import com.example.demo.repository.CinemaRepository;

@Controller
public class CinemaController {

	@Autowired
	CinemaRepository repository;
	
	@GetMapping("/cinema")
	public String listCinema(Model model) {
		List<Cinema> cinema = repository.findAll();
		model.addAttribute("cinema", cinema);
		return "cinema-list";
	}
	
	@GetMapping("/cinemas/{id}/view")
	public String viewCinema(@PathVariable Long id, Model model) {
		if (id == null) {
			return "redirect:/cinemas";
		}
		Optional<Cinema> manOpt = repository.findById(id);
		if (manOpt.isPresent()) {
			model.addAttribute("cinema", manOpt.get());
			return "cinema-view";
		}
		return "redirect:/cinemas";
	}
	
	@GetMapping("/cinemas/new")
	public String showForm(Model model) {
		model.addAttribute("cinema", new CinemaController());
		return "cinema-edit";
	}
	
	@PostMapping("/cinemas")
	public String saveCinema(@ModelAttribute("cinema") CinemaController cinema) {
		CinemaRepository.save(cinema);
		return "redirect:/cinemas";
	}
	
}