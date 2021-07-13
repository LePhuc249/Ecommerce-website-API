package nashtech.phucldh.ecommerce.exception;

import java.time.LocalDateTime;

public class ErrorDetail {

	public LocalDateTime time;

	private String message;

	private String description;

	public ErrorDetail() {
	}

	public ErrorDetail(LocalDateTime time, String message, String description) {
		this.time = time;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
