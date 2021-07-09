package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDTO {
	
	private String feedbackid;

	private String accountid;

	private String orderid;

	private String content;

	private int counter;

	private String status;
}
