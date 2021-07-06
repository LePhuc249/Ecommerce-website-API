package nashtech.phucldh.ecommerce.payload.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginRequest {
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{7,30}", message = "Invalid username!")
	private String username;

	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,100}$", message = "Invalid password!")
	private String password;

	public LoginRequest() {
	}

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password);
	}

}
