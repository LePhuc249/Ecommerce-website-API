package nashtech.phucldh.ecommerce.exception;

public class PaymentmethodNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PaymentmethodNotFoundException(String message) {
		super(message);
	}
}
