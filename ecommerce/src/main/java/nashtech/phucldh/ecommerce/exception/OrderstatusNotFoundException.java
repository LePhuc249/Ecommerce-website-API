package nashtech.phucldh.ecommerce.exception;

public class OrderstatusNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderstatusNotFoundException(String message) {
		super(message);
	}
}
