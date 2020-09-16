package it.uniroma3.siw.booking.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Room room;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkin;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkout;
	
	@ManyToOne
	private User booker;
	
	public Book() {
		
	}
	
	public Book(LocalDate checkin, LocalDate checkout) {
		this();
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getBooker() {
		return booker;
	}

	public void setBooker(User booker) {
		this.booker = booker;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public static List<LocalDate> getDatesBetween(LocalDate checkin, LocalDate checkout) { 
		long numOfDaysBetween = ChronoUnit.DAYS.between(checkin, checkout); 
	    return IntStream.iterate(0, i -> i + 1)
	    				.limit(numOfDaysBetween)
	    				.mapToObj(i -> checkin.plusDays(i))
	    				.collect(Collectors.toList()); 
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", room=" + room + ", checkin=" + checkin + ", checkout=" + checkout + ", booker="
				+ booker + "]";
	}


	
}
