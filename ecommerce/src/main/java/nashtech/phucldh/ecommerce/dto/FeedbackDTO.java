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
public class FeedbackDTO {
	
	private Integer id;

	private Integer account_id;

	private Integer order_id;
	
	private LocalDateTime createdate;
	
	private LocalDateTime updatedate;

	private String content;

	private int counter;

	private boolean isDeleted;
}
