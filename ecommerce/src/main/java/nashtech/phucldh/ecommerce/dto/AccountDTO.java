package nashtech.phucldh.ecommerce.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
	
	private UUID id;
	
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String statusaccount;
	
	private RoleDTO role;
}
