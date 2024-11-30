package com.application.jobportal.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.jobportal.entity.Users;
import com.application.jobportal.entity.UsersType;
import com.application.jobportal.service.UsersService;
import com.application.jobportal.service.UsersTypeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UsersController {
	private final UsersTypeService usersTypeService;
	private final UsersService usersService;

	public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
		super();
		this.usersTypeService = usersTypeService;
		this.usersService = usersService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		List<UsersType>usersTypes = usersTypeService.getAll();
		model.addAttribute("getAllTypes", usersTypes);
		model.addAttribute("user", new Users());
		return "register";
	}
	
	@PostMapping("/register/new")
	public String userRegistration(@Valid Users user,Model model) {
		Optional<Users> userByEmail = usersService.getUserByEmail(user.getEmail());
		
		if(userByEmail.isPresent()) {
			model.addAttribute("error","Email already Registered , Try with another email");
			List<UsersType>usersTypes = usersTypeService.getAll();
			model.addAttribute("getAllTypes", usersTypes);
			model.addAttribute("user", new Users());
			return "register";
		}
		usersService.addNew(user);	
		return "redirect:/dashboard/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/logout")
	public String logout (HttpServletRequest request,HttpServletResponse response) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}
}
