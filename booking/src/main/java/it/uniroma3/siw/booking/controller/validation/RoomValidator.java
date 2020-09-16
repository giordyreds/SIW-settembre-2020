package it.uniroma3.siw.booking.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.booking.model.Room;
import it.uniroma3.siw.booking.model.User;
import it.uniroma3.siw.booking.service.RoomService;

@Component
public class RoomValidator implements Validator {

	final Integer MAX_NUMBER_LENGTH = 3;
	final Integer MIN_NUMBER_LENGTH = 1;
	
	@Autowired
	RoomService roomService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Room room = (Room) o;
		String number = room.getNumber().trim();
		
		if(number.isEmpty() || number == null)
			errors.rejectValue("number", "required");
		else if(number.length() < MIN_NUMBER_LENGTH || number.length() > MAX_NUMBER_LENGTH)
			errors.rejectValue("number", "size");
		else if (this.roomService.getRoom(number) != null)
            errors.rejectValue("number", "duplicate");
	}
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
}
