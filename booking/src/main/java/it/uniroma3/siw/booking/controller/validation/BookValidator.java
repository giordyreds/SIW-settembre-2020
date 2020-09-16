package it.uniroma3.siw.booking.controller.validation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.service.BookService;

@Component
public class BookValidator implements Validator {

	@Autowired
	BookService bookService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Book book = (Book) o;
		LocalDate checkin = book.getCheckin();
		LocalDate checkout = book.getCheckout();
		//Room room = book.getRoom();
		
		//List<Book> bookByRoom = this.bookService.retrieveBooksByRoom(room);
		
		if(Book.getDatesBetween(checkin, checkout).isEmpty() || Book.getDatesBetween(checkin, checkout)==null)
			errors.rejectValue("checkin", "duplicate");
	}

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
}
