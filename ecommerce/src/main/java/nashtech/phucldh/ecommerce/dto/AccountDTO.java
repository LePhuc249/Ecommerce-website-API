package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AccountDTO {

	private Integer id;

	private String username;

	private String fullname;

	private Integer status;

	private Set<String> roles = new HashSet<>();

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	public AccountDTO() {
	}

	public AccountDTO(Integer id, String username, String fullname, Integer status, Set<String> roles,
			LocalDateTime createdate, LocalDateTime updatedate) {
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.status = status;
		this.roles = roles;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public LocalDateTime getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", username=" + username + ", fullname=" + fullname + ", status=" + status
				+ ", roles=" + roles + ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}

}
