package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Feedback;

public interface FeedbackService {
	
	public List<Feedback> findAll();
	
	public Feedback getFeedbackById(String code);
	
	public void saveFeedback(Feedback theFeedback);
	
	public void deleteFeedbackById(String idFeedback);
}
