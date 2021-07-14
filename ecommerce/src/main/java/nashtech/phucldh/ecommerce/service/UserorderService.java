package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Account_Order;

public interface UserorderService {

	public List<Account_Order> findAll();

	public List<Account_Order> findOrderOfCustomer(String accountID);

	public List<String> listUsedCoupons(String id);

	public void saveOrder(Account_Order newUserOrder);

	public Account_Order getOrderById(String orderID);

	public boolean updateStatusToFinish(String orderId);
	
	public boolean updateStatusToCancel(String orderId);
	
	public boolean updateStatusToConfirm(String orderId);
}
