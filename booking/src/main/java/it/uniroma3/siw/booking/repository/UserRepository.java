package it.uniroma3.siw.booking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByMyBooks(Book book);
}
