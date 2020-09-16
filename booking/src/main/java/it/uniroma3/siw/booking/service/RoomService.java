package it.uniroma3.siw.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Transactional
	public Room getRoom(Long id) {
		Optional<Room> result = this.roomRepository.findById(id);
		return result.orElse(null);
	}
	
	@Transactional
	public Room getRoom(String number) {
		Optional<Room> result = this.roomRepository.findByNumber(number);
		return result.orElse(null);
	}
	
	@Transactional
	public Room saveRoom(Room room) {
		return this.roomRepository.save(room);
	}
	
	@Transactional
	public List<Room> findAllRooms() {
		Iterable<Room> iterable = this.roomRepository.findAll();
		ArrayList<Room> list = new ArrayList<>();
		for(Room room : iterable)
			list.add(room);
		return list;
	}
	
	public List<Room> retrieveRoomsBySeats(Integer seats) {
		Iterable<Room> iterable = this.roomRepository.findBySeats(seats);
		ArrayList<Room> list = new ArrayList<>();
		for(Room room : iterable) 
			list.add(room);
		return list;
	}
	
}
