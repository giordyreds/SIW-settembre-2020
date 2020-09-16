package it.uniroma3.siw.booking.controller.validation;

import it.uniroma3.siw.booking.model.Credentials;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.service.CredentialsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for Credentials
 */
@Component
public class CredentialsValidator implements Validator {

    final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 6;

    @Autowired
    CredentialsService credentialsService;

    @Override
    public void validate(Object o, Errors errors) {
        Credentials credentials = (Credentials) o;
        String userName = credentials.getUserName().trim();
        String password = credentials.getPassword().trim();

        if (userName.isEmpty() || userName==null)
            errors.rejectValue("userName", "required");
        else if (userName.length() < MIN_USERNAME_LENGTH || userName.length() > MAX_USERNAME_LENGTH)
            errors.rejectValue("userName", "size");
        else if (this.credentialsService.getCredentials(userName) != null)
            errors.rejectValue("userName", "duplicate");

        if (password.isEmpty() || password==null)
            errors.rejectValue("password", "required");
        else if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
            errors.rejectValue("password", "size");
    }
    public void checkUserNameEntered(Object o, Errors errors) {
		Credentials credentials = (Credentials) o;
		String username = credentials.getUserName();
		if(username.trim().isEmpty()) 
			errors.rejectValue("userName","required");
		else if(this.credentialsService.getCredentials(username) == null)
			errors.rejectValue("userName", "doesntExist");
		
	}
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}