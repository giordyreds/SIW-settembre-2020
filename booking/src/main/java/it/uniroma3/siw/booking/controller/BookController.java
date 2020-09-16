package it.uniroma3.siw.booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = {"/bookRoom"}, method = RequestMethod.GET)
	public String bookForm(Model model) {
		User loggedUser = sessionData.getLoggedUser();
	
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("roomForm", new Room());
	
		return "addBook";	
}

	@RequestMapping(value = {"/bookRoom"}, method = RequestMethod.POST)
	public String createBook(@Valid @ModelAttribute("roomForm") Room roomForm,
				BindingResult roomBindingResult,
						Model model) {
		
		List<Room> roomList = this.roomService.retrieveRoomsBySeats(roomForm.getSeats());
		
		roomList.removeIf(room->room.isBooked()==true);
		
		model.addAttribute("roomList", roomList);

		model.addAttribute("roomForm", roomForm);
	
		return "roomSearch";
	}
	
	@RequestMapping(value = {"/bookRoom/roomView/{roomId}"}, method = RequestMethod.GET)
	public String roomView(Model model,
					   @PathVariable Long roomId) {
		User loggedUser = sessionData.getLoggedUser();
		Room room = roomService.getRoom(roomId);
		if(room == null)
			return "redirect:/bookRoom";
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("room", room);
		
		return "roomView";
	}
	
	@RequestMapping(value = {"/bookRoom/roomView/{roomId}/book"}, method = RequestMethod.GET)
	public String roomBookForm(Model model,@PathVariable Long roomId) {
		User loggedUser = sessionData.getLoggedUser();	
		Room room = this.roomService.getRoom(roomId);
		if(room == null)
			return "roomSearch";
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("bookForm", new Book());
		return "bookForm";
	}
	
	@RequestMapping(value = {"/bookRoom/roomView/{roomId}/book"}, method = RequestMethod.POST)
	public String roomBook(@Valid @ModelAttribute("bookForm") Book book, @PathVariable Long roomId,
								BindingResult bookBindingResult, Model model) {
		User loggedUser = sessionData.getLoggedUser();
		Room room = this.roomService.getRoom(roomId);
		
		if(room == null) {
			return "roomSearch";
		}
		
		bookValidator.validate(book, bookBindingResult);
		if (!bookBindingResult.hasErrors()) {
			book.setRoom(room);
			book.setBooker(loggedUser);
			room.setBooked(true);
			this.bookService.saveBook(book);
			return "bookSuccess"; //+ room.getId();
		}
		return "bookForm";		
		
	}
	
}
