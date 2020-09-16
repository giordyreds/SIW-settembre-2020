package it.uniroma3.siw.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	
	public List<Book> findByBooker(User booker);
	
	public List<Book> findByRoom(Room room);
	
	public List<Book> findByCheckin(LocalDate date);
	
	public List<Book> findByCheckout(LocalDate date);
	
}
