package nashtech.phucldh.ecommerce.payload.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@NotBlank(message = "Username is required.")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{7,30}$", message = "Invalid username!")
	private String username;

	@NotBlank(message = "Password is required.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,100}$", message = "Invalid password!")
	private String password;

	private String retypePassword;

	@NotBlank(message = "Full name is required.")
	@Size(min = 1, max = 50, message = "Full name accepts only upto 50 characters and minimum 1 character")
	private String fullname;

	@NotBlank(message = "Email is required.")
	@Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,5}(\\.[a-z0-9]{2,4}){1,2}$", message = "Invalid email!")
	private String email;

	@Pattern(regexp = "[0-9]{7,12}", message = "Invalid phone number!")
	private String phoneNumber;

	private int status;

	private Set<String> role = new HashSet<>();

	public SignUpRequest() {
	}
	
	public SignUpRequest(String username, String password, String fullname, String email, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public SignUpRequest(String username, String password, String fullname, String email, String phoneNumber,
			Set<String> role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public SignUpRequest(String username, String password, String fullname, String email, String phoneNumber,
			int statusaccount, Set<String> role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = statusaccount;
		this.role = role;
	}

	public SignUpRequest(String username, String password, String retypePassword, String fullname, String email,
			String phoneNumber, int statusaccount, Set<String> role) {
		this.username = username;
		this.password = password;
		this.retypePassword = retypePassword;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = statusaccount;
		this.role = role;
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

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatusaccount() {
		return status;
	}

	public void setStatusaccount(int statusaccount) {
		this.status = statusaccount;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
