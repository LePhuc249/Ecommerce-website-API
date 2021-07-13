package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
	
	private Integer id;
	
	private String username;
	
	private String fullname;
	
	private Integer status;
	
	private Set<String> roles = new HashSet<>();
	
	private LocalDateTime createdate;
	
	private LocalDateTime updatedate;
}
