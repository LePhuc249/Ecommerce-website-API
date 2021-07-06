package nashtech.phucldh.ecommerce.exception;

public class CouponsNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CouponsNotFoundException(String message) {
		super(message);
	}
}
