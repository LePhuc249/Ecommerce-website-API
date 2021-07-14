package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.AccountOrderRepository;
import nashtech.phucldh.ecommerce.service.AccountOrderService;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {
	
	@Autowired
	AccountOrderRepository userorderRepository;

	@Override
	public List<AccountOrder> findAll() {
		List<AccountOrder> theListUserorder = userorderRepository.findAll();
		return theListUserorder;
	}

	@Override
	public List<AccountOrder> findOrderOfCustomer(String accountID) {
		List<AccountOrder> result = userorderRepository.findByCustomerid(accountID);
		return result;
	}

	@Override
	public List<String> listUsedCoupons(String id) {
		List<String> result = userorderRepository.findByCouponid(id);
		return result;
	}

	@Override
	public void saveOrder(AccountOrder newUserOrder) {
		userorderRepository.save(newUserOrder);
	}

	@Override
	public AccountOrder getOrderById(Integer orderID) throws DataNotFoundException {
		Optional<AccountOrder> result = userorderRepository.findById(orderID);
		AccountOrder theUserorder = null;
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
