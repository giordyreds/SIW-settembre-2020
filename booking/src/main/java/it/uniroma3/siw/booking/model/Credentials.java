package it.uniroma3.siw.booking.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Credentials {
	
	public static final String DEFAULT_ROLE = "CUSTOMER";
	public static final String ADMIN_ROLE = "RECEPTION";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 100)
	private String userName;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 10)
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Credentials() {
		
	}
	
	public Credentials(String userName, String password) {
    	this();
    	this.userName = userName;
    	this.password = password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;
        Credentials user = (Credentials) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(role, user.role);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(userName, role);
    }
	
	@Override
	public String toString() {
		return "Credentials [id=" + id + ", userName=" + userName + ", role=" + role + "]";
	}
}