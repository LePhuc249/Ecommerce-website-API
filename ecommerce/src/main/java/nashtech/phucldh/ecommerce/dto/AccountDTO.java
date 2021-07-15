package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountDTO {

	private Long id;

	private String username;

	private String fullname;

	private Integer status;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private Set<String> roles = new HashSet<>();

}
