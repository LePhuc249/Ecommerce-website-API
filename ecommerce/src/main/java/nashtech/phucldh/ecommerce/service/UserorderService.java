package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Userorder;

public interface UserorderService {

	public List<Userorder> findAll();

	public List<Userorder> findOrderOfCustomer(String accountID);

	public List<String> listUsedCoupons();

	public List<Userorder> historyOrder(String date);

	public void saveOrder(Userorder newUserOrder);

	public Userorder getOrderById(String orderID);

	public boolean updateFinish(String orderId);

	public boolean updateCancel(String orderId);

	public boolean updateConfirm(String orderId);
}
