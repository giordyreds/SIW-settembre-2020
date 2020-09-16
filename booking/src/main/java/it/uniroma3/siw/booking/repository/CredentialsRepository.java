package it.uniroma3.siw.booking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.booking.model.Credentials;



public interface CredentialsRepository extends CrudRepository<Credentials, Long>{

	public Optional<Credentials> findByUserName(String username);
	
	public void delete(Credentials credentials);
}
