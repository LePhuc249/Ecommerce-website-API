package nashtech.phucldh.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountExistedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AccountExistedException(String message) {
		super(message);
	}
}
