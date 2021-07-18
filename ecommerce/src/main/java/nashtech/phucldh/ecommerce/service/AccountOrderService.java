package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface AccountOrderService {

	public List<AccountOrder> findAll() throws DataNotFoundException;

	public List<AccountOrder> findOrderOfCustomer(Long accountID) throws DataNotFoundException;

	public List<String> listUsedCoupons(Long id) throws DataNotFoundException;

	public void createNewOrder(AccountOrder newUserOrder) throws CreateDataFailException;

	public AccountOrder getOrderById(Long orderID) throws DataNotFoundException;

	public boolean updateStatusToFinish(Long orderId) throws UpdateDataFailException;
	
	public boolean updateStatusToCancel(Long orderId) throws UpdateDataFailException;

	public boolean updateStatusToConfirm(Long orderId) throws UpdateDataFailException;

	public boolean updateStatusToProcess(Long orderId) throws UpdateDataFailException;

	public Long getStatusOfOrder(Long orderID) throws DataNotFoundException;
}
