package nashtech.phucldh.ecommerce.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import nashtech.phucldh.ecommerce.payload.response.MessageResponse;

@Component
public class AccountResponse {

	public ResponseEntity<?> generateMessageResponseEntity(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(new MessageResponse(message));
	}
}
