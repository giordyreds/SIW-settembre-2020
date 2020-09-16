package it.uniroma3.siw.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.booking.repository.UserRepository;
import it.uniroma3.siw.booking.service.CredentialsService;
import it.uniroma3.siw.booking.model.Credentials;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.controller.session.SessionData;
import it.uniroma3.siw.booking.controller.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CredentialsService credentialsService;
	
	@Autowired
    UserValidator userValidator;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    SessionData sessionData;
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
    	User loggedUser = sessionData.getLoggedUser();
    	model.addAttribute("loggedUser", loggedUser);
    	return "home";
    }
    
    @RequestMapping(value = { "/users/me" }, method = RequestMethod.GET)
    public String me(Model model) {
        User loggedUser = sessionData.getLoggedUser();
        Credentials credentials = sessionData.getLoggedCredentials();
        System.out.println(credentials.getPassword());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("credentials", credentials);
        return "userProfile";
    }
    
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin(Model model) {
        User loggedUser = sessionData.getLoggedUser();
        model.addAttribute("loggedUser", loggedUser);
        return "admin";
    }
    
    
    
}
