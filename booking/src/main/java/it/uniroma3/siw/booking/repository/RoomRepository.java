package it.uniroma3.siw.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.booking.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

	public Optional<Room> findByNumber(String number);

	public List<Room> findBySeats(Integer seats);
	
}
