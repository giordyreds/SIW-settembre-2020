package it.uniroma3.siw.booking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

	//public Optional<Book> findByRoom(Room room);
	
	public List<Book> findByBooker(User booker);
	
	public List<Book> findByRoom(Room room);
	
	//public Optional<Book> findByCheckin(LocalDate checkin);
	
	//public Optional<Book> findByCheckout(LocalDate checkout);
	
	public List<Book> findByCheckin(LocalDate date);
	
	public List<Book> findByCheckout(LocalDate date);
	
}
