package nashtech.phucldh.ecommerce.payload.response;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private String fullname;
	private String email;
	private String roleid;

	public JwtResponse() {
	}

	public JwtResponse(String token, String username, String fullname, String email, String roleid) {
		this.token = token;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.roleid = roleid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
