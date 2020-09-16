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
import it.uniroma3.siw.booking.controller.validation.CredentialsValidator;
import it.uniroma3.siw.booking.controller.validation.RoomValidator;
import it.uniroma3.siw.booking.repository.BookRepository;
import it.uniroma3.siw.booking.repository.RoomRepository;
import it.uniroma3.siw.booking.service.BookService;
import it.uniroma3.siw.booking.service.CredentialsService;
import it.uniroma3.siw.booking.service.RoomService;
import it.uniroma3.siw.booking.service.UserService;
import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;

//fare date format per controllare l'input
@Controller
public class RoomController {
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CredentialsService credentialsService;
	
	@Autowired
	RoomValidator roomValidator;
	
	@Autowired
	BookValidator bookValidator;
	
	@Autowired
	CredentialsValidator credentialsValidator;
	
	@Autowired
	SessionData sessionData;
	
	//LATO ADMIN
	@RequestMapping(value = {"/rooms"}, method = RequestMethod.GET)
	public String allRooms(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		
		List<Room> roomList = this.roomService.findAllRooms();
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("roomList", roomList);
		return "allRooms";
	}
	
	@RequestMapping(value = {"/rooms/{roomId}"}, method = RequestMethod.GET)
	public String room(Model model,
					   @PathVariable Long roomId) {
		User loggedUser = sessionData.getLoggedUser();
		Room room = this.roomService.getRoom(roomId);
		if(room == null)
			return "redirect:/rooms";
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("room", room);
		
		return "room";
	}
	
	@RequestMapping(value = {"/notBooked"}, method = RequestMethod.GET)
	public String notBooked(Model model) {
		List<Room> roomList = this.roomService.findAllRooms();
		
		roomList.removeIf(room->room.isBooked()==true);
		
		model.addAttribute("roomList", roomList);
		return "notBooked";
	}
	
	@RequestMapping(value = {"/dateSearch"}, method = RequestMethod.GET)
	public String searchByDate(Model model) {
		return "dateSearch";
	}
	
	@RequestMapping(value = {"/checkinSearch"}, method = RequestMethod.GET)
	public String searchByCheckin(Model model) {
		model.addAttribute("bookForm", new Book());
		return "checkinSearchForm";
	}
	
	@RequestMapping(value = {"/checkinSearch"}, method = RequestMethod.POST)
	private String searchByCheckin(@Valid @ModelAttribute("bookForm") Book bookForm,
			BindingResult bookBindingResult,
			Model model) {
		
		List<Book> bookList = this.bookService.retrieveBooksByCheckin(bookForm.getCheckin());
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("bookForm", bookForm);
		return "checkinSearch";
	}
	
	@RequestMapping(value = {"/checkoutSearch"}, method = RequestMethod.GET)
	public String searchByCheckout(Model model) {
		model.addAttribute("bookForm", new Book());
		return "checkoutSearchForm";
	}
	
	@RequestMapping(value = {"/checkoutSearch"}, method = RequestMethod.POST)
	private String searchByCheckout(@Valid @ModelAttribute("bookForm") Book bookForm,
			BindingResult bookBindingResult,
			Model model) {
		
		List<Book> bookList = this.bookService.retrieveBooksByCheckout(bookForm.getCheckout());
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("bookForm", bookForm);
		return "checkoutSearch";
	}
	
	@RequestMapping(value = {"/rooms/add"}, method = RequestMethod.GET)
	public String createRoomForm(Model model) {
		User loggedUser = sessionData.getLoggedUser();		
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("roomForm", new Room());
		return "addRoom";
	}
	
	@RequestMapping(value = {"/rooms/add"}, method = RequestMethod.POST)
	public String createRoom(@Valid @ModelAttribute("roomForm") Room room,
			                    BindingResult roomBindingResult,
			                    Model model) {

		roomValidator.validate(room, roomBindingResult);
		if (!roomBindingResult.hasErrors()) {
			this.roomService.saveRoom(room);
			return "redirect:/rooms";
		}
		return "addRoom";
	}

	//LATO USER
	
	@RequestMapping(value = {"/myBooks"}, method = RequestMethod.GET)
	public String myBooks(Model model) {
		User loggedUser = sessionData.getLoggedUser();
		
		List<Book> bookList = this.bookService.retrieveBooksByBooker(loggedUser);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("bookList", bookList);
		return "myBooks";
	}
	
	@RequestMapping(value = {"/myBooks/{roomId}"}, method = RequestMethod.GET)
	public String myBook(Model model,
					   @PathVariable Long roomId) {
		User loggedUser = sessionData.getLoggedUser();
		Room room = roomService.getRoom(roomId);
		if(room == null)
			return "redirect:/myBooks";
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("room", room);
		
		return "myBook";
	}
}