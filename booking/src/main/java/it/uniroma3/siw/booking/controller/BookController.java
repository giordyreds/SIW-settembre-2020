package it.uniroma3.siw.booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.booking.controller.session.SessionData;
import it.uniroma3.siw.booking.controller.validation.BookValidator;
import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.repository.BookRepository;
import it.uniroma3.siw.booking.repository.RoomRepository;
import it.uniroma3.siw.booking.service.BookService;
import it.uniroma3.siw.booking.service.CredentialsService;
import it.uniroma3.siw.booking.service.RoomService;
import it.uniroma3.siw.booking.service.UserService;

@Controller
public class BookController {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CredentialsService credentialsService;
	
	@Autowired
	BookValidator bookValidator;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	SessionData sessionData;
	
	/*@RequestMapping(value = {"/bookRoom"}, method = RequestMethod.GET)
	public String bookForm(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		
		model.addAttribute("loggedUser", loggedUser);
    	model.addAttribute("bookForm", new Room());
    	
    	return "addBook";
	}
	
	@RequestMapping(value = {"/bookRoom"}, method = RequestMethod.POST)
	public String createBook(@Valid @ModelAttribute("bookForm") Room bookForm,
							BindingResult bookBindingResult,
							Model model) {
		
		List<Room> roomList = this.roomService.retrieveRoomsBySeats(bookForm.getSeats());
		
		model.addAttribute("roomList", roomList);
		model.addAttribute("bookForm", bookForm);
		System.out.println(roomList);
		
		return "roomSearch";
	}*/
	
	
}
