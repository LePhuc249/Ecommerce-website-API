package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Account_Order;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface Account_OrderService {

	public List<Account_Order> findAll();

	public List<Account_Order> findOrderOfCustomer(String accountID);

	public List<String> listUsedCoupons(String id);

	public void saveOrder(Account_Order newUserOrder);

	public Account_Order getOrderById(Integer orderID) throws DataNotFoundException;

	public boolean updateStatusToFinish(String orderId);
	
	public boolean updateStatusToCancel(String orderId);
	
	public boolean updateStatusToConfirm(String orderId);
}
