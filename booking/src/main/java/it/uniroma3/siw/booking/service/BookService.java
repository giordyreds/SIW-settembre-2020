package it.uniroma3.siw.booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Transactional
	public Book getBook(Long id) {
		Optional<Book> result = this.bookRepository.findById(id);
		return result.orElse(null);
	}
	
	/*@Transactional
	public Book getBook(Room room) {
		Optional<Book> result = this.bookRepository.findByRoom(room);
		return result.orElse(null);
	}*/
	
	/*@Transactional
	public Book getBookByCheckin(LocalDate checkin) {
		Optional<Book> result = this.bookRepository.findByCheckin(checkin);
		return result.orElse(null);
	}*/

	/*@Transactional
	public Book getBookByCheckout(LocalDate checkout) {
		Optional<Book> result = this.bookRepository.findByCheckout(checkout);
		return result.orElse(null);
	}*/

	@Transactional
	public void deleteBook(Book book) {
		this.bookRepository.delete(book);
	}
	
	@Transactional
	public Book saveBook(Book book) {
		return this.bookRepository.save(book);
	}
	
	@Transactional
	public List<Book> retrieveBooksByRoom(Room room) { 
		Iterable<Book> iterable = this.bookRepository.findByRoom(room);
		ArrayList<Book> list = new ArrayList<>();
		for(Book book : iterable)
			list.add(book);
		return list;
	} 
	
	@Transactional
	public List<Book> retrieveBooksByBooker(User booker) {
		Iterable<Book> iterable = this.bookRepository.findByBooker(booker);
		ArrayList<Book> list = new ArrayList<>();
		for(Book book : iterable)
			list.add(book);
		return list;
	}
	
	public List<LocalDate> retrieveRoomByDates(LocalDate checkin, LocalDate checkout) {
		Iterable<LocalDate> iterable = Book.getDatesBetween(checkin, checkout);
		ArrayList<LocalDate> list = new ArrayList<>();
		for(LocalDate date : iterable)
			list.add(date);
		return list;
	}
	
	public List<Book> retrieveBooksByCheckin(LocalDate date) {
		Iterable<Book> iterable = this.bookRepository.findByCheckin(date);
		ArrayList<Book> list = new ArrayList<>();
		for(Book book : iterable)
			list.add(book);
		return list;
	}
	
	public List<Book> retrieveBooksByCheckout(LocalDate date) {
		Iterable<Book> iterable = this.bookRepository.findByCheckout(date);
		ArrayList<Book> list = new ArrayList<>();
		for(Book book : iterable)
			list.add(book);
		return list;
	}
}
