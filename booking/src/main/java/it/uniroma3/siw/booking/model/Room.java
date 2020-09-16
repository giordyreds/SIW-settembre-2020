package it.uniroma3.siw.booking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private boolean booked;
	
	@Column(nullable = false, length = 3)
	private String number;
	
	@Column(length = 2000)
	private String description;
	
	@Column(nullable = false)
	private Integer seats;
	
	@Column(nullable = false)
	private Integer beds;
	
	@Column(nullable = false)
	private Integer singleBeds;
	
	@Column(nullable = false)
	private Integer doubleBeds;
	
	@Column(nullable = false)
	private Float price;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private List<Book> books;

	public Room() {
		this.books = new ArrayList<>();
	}
	
	public Room(String number, String description, Integer seats, 
			Integer beds, Integer singleBeds, Integer doubleBeds, Float price, boolean booked) {
		
		this();
		this.number = number;
		this.description = description;
		
		this.seats = seats;
		this.beds = beds;
		this.singleBeds = singleBeds;
		this.doubleBeds = doubleBeds;
		this.price = price;
		this.booked = booked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Integer getSingleBeds() {
		return singleBeds;
	}

	public void setSingleBeds(Integer singleBeds) {
		this.singleBeds = singleBeds;
	}

	public Integer getDoubleBeds() {
		return doubleBeds;
	}

	public void setDoubleBeds(Integer doubleBeds) {
		this.doubleBeds = doubleBeds;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	
	
}
