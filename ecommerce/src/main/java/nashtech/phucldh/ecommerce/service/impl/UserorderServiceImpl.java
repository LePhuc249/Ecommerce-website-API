package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Userorder;
import nashtech.phucldh.ecommerce.exception.UserorderNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.UserorderRepository;
import nashtech.phucldh.ecommerce.service.UserorderService;

@Service
public class UserorderServiceImpl implements UserorderService{
	
	@Autowired
	UserorderRepository userorderRepository;
	
	@Override
	public List<Userorder> findAll() {
		List<Userorder> theListUserorder = userorderRepository.findAll();
		return theListUserorder;
	}

	@Override
	public List<Userorder> findOrderOfCustomer(String accountID) {
		List<Userorder> result = userorderRepository.findByCustomerid(accountID);
		return result;
	}

	@Override
	public List<String> listUsedCoupons() {
		List<String> result = userorderRepository.findByCouponid();
		return result;
	}

	@Override
	public void saveOrder(Userorder newUserOrder) {
		userorderRepository.save(newUserOrder);
	}

	@Override
	public Userorder getOrderById(String orderID) {
		Optional<Userorder> result = userorderRepository.findById(orderID);
		Userorder theUserorder = null;
		if (result.isPresent()) {
			theUserorder = result.get();
		} else {
			throw new UserorderNotFoundException("Did not find order of user by code - " + orderID);
		}
		return theUserorder;
	}

	@Override
	public boolean updateStatusToFinish(String orderId) {
		boolean result = false;
		result = userorderRepository.updateStatusToFinish(orderId);
		return result;
	}

	@Override
	public boolean updateStatusToCancel(String orderId) {
		boolean result = false;
		result = userorderRepository.updateStatusToCancel(orderId);
		return result;
	}

	@Override
	public boolean updateStatusToConfirm(String orderId) {
		boolean result = false;
		result = userorderRepository.updateStatusToConfirm(orderId);
		return result;
	}

}
