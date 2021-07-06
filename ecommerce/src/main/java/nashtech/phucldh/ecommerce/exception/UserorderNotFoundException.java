package nashtech.phucldh.ecommerce.exception;

public class UserorderNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserorderNotFoundException(String message) {
		super(message);
	}
}
