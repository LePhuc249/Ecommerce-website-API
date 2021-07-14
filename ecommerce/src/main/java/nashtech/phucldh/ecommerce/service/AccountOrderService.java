package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface AccountOrderService {

	public List<AccountOrder> findAll();

	public List<AccountOrder> findOrderOfCustomer(String accountID);

	public List<String> listUsedCoupons(String id);

	public void saveOrder(AccountOrder newUserOrder);

	public AccountOrder getOrderById(Integer orderID) throws DataNotFoundException;

	public boolean updateStatusToFinish(String orderId);
	
	public boolean updateStatusToCancel(String orderId);
	
	public boolean updateStatusToConfirm(String orderId);
}
