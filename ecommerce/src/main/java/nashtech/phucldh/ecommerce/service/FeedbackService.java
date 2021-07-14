package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Feedback;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface FeedbackService {
	
	public List<Feedback> findAll();
	
	public Feedback getFeedbackById(Integer code) throws DataNotFoundException;
	
	public void saveFeedback(Feedback theFeedback);
	
	public void deleteFeedbackById(Integer idFeedback);
	
	public boolean checkExistFeedback(String username, String orderid);
}
