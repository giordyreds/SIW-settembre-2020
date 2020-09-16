package it.uniroma3.siw.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.booking.model.Book;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User getUser(Long id) {
		Optional<User> result = this.userRepository.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Transactional
	public List<User> findAllUsers() {
		Iterable<User> iterable = this.userRepository.findAll();
		ArrayList<User> list = new ArrayList<>();
		for(User user : iterable)
			list.add(user);
		return list;
	}
	
	/*public User getBooker(Room room) {
		User result = this.userRepository.findByReservedRooms(room);
		return result;
	}*/
	
	@Transactional
	public List<User> getBookers(Book book) {
		Iterable<User> iterable = this.userRepository.findByMyBooks(book);
		List<User> list = new ArrayList<>();
		for(User user : iterable)
			list.add(user);
		return list;
	}
	
}
