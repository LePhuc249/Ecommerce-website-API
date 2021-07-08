package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Feedback;
import nashtech.phucldh.ecommerce.exception.FeedbackNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.FeedbackRepository;
import nashtech.phucldh.ecommerce.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public List<Feedback> findAll() {
		List<Feedback> theListFeedback = feedbackRepository.findAll();
		return theListFeedback;
	}

	@Override
	public Feedback getFeedbackById(String code) {
		Optional<Feedback> result = feedbackRepository.findById(code);
		Feedback theFeedback = null;
		if (result.isPresent()) {
			theFeedback = result.get();
		} else {
			throw new FeedbackNotFoundException("Did not find feedback by code - " + code);
		}
		return theFeedback;
	}

	@Override
	public void saveFeedback(Feedback theFeedback) {
		feedbackRepository.save(theFeedback);
	}

	@Override
	public void deleteFeedbackById(String idFeedback) {
		feedbackRepository.deleteById(idFeedback);
	}

	@Override
	public boolean checkExistFeedback(String username, String orderid) {
		boolean result = feedbackRepository.existsFeedbackByUsernameAndOrderid(username, orderid);
		return result;
	}

}
