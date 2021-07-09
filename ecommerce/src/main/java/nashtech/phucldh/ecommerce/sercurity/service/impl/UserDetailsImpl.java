package nashtech.phucldh.ecommerce.sercurity.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	private String fullname;

	private String email;

	private String statusaccount;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(String username, String password, String fullname, String email, String statusaccount,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.statusaccount = statusaccount;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Account theAccount) {
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(theAccount.getRole());
		List<GrantedAuthority> authorities = roleSet.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
		return new UserDetailsImpl(theAccount.getUsername(), theAccount.getPassword(), theAccount.getFullname(),
				theAccount.getEmail(), theAccount.getStatusaccount(), authorities);
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

	public String getStatusaccount() {
		return statusaccount;
	}

	public void setStatusaccount(String statusaccount) {
		this.statusaccount = statusaccount;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", email=" + email + ", statusaccount=" + statusaccount + ", authorities=" + authorities + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		boolean status = false;
		if(statusaccount.equals("Active")) {
			status = true;
		}
		return status;
	}
}
