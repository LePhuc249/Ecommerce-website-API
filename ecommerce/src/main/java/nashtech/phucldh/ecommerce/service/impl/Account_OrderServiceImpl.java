package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Account_Order;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.Account_OrderRepository;
import nashtech.phucldh.ecommerce.service.Account_OrderService;

@Service
public class Account_OrderServiceImpl implements Account_OrderService{
	
	@Autowired
	Account_OrderRepository userorderRepository;

	@Override
	public List<Account_Order> findAll() {
		List<Account_Order> theListUserorder = userorderRepository.findAll();
		return theListUserorder;
	}

	@Override
	public List<Account_Order> findOrderOfCustomer(String accountID) {
		List<Account_Order> result = userorderRepository.findByCustomerid(accountID);
		return result;
	}

	@Override
	public List<String> listUsedCoupons(String id) {
		List<String> result = userorderRepository.findByCouponid(id);
		return result;
	}

	@Override
	public void saveOrder(Account_Order newUserOrder) {
		userorderRepository.save(newUserOrder);
	}

	@Override
	public Account_Order getOrderById(Integer orderID) throws DataNotFoundException {
		Optional<Account_Order> result = userorderRepository.findById(orderID);
		Account_Order theUserorder = null;
		if (result.isPresent()) {
			theUserorder = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
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
