package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User userForm, HttpSession session) {
		System.out.println(userForm);
		Optional<User> userDB = userRepository.findByEmailAndPassword(userForm.getEmail(), userForm.getPassword());
		if (userDB.isPresent()) {
			session.setAttribute("user", userDB.get());
		}
		return "redirect:/movies";
	}
	
}
