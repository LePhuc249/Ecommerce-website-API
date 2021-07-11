package nashtech.phucldh.ecommerce.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDTO {
	
	private UUID feedbackid;

	private UUID accountid;

	private UUID orderid;
	
	private Timestamp feedbacktime;

	private String content;

	private int counter;

	private String status;
}
