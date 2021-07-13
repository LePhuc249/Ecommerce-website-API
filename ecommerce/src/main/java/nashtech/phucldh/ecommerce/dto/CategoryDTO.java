package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private Integer id;

	private String name;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private Integer create_account;

	private boolean isDeleted;

}
