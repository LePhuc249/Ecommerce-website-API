package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Userorder;

public interface UserorderService {

	public List<Userorder> findAll();

	public List<Userorder> findOrderOfCustomer(String accountID);

	public List<String> listUsedCoupons();

	public void saveOrder(Userorder newUserOrder);

	public Userorder getOrderById(String orderID);

	public boolean updateStatusToFinish(String orderId);
	
	public boolean updateStatusToCancel(String orderId);
	
	public boolean updateStatusToConfirm(String orderId);
}
