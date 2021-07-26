package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.dto.AccountOrderDTO;
import nashtech.phucldh.ecommerce.entity.AccountOrder;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface AccountOrderService {

    public List<AccountOrder> findAll() throws DataNotFoundException;

    public List<AccountOrder> findOrderOfCustomer(Long accountID) throws DataNotFoundException;

    public List<String> listUsedCoupons(Long id) throws DataNotFoundException;

    public AccountOrder getOrderById(Long orderID) throws DataNotFoundException;

    public AccountOrder getAccountOrder(Long accountId, int totalPrice) throws DataNotFoundException;

    public Boolean createNewOrder(AccountOrderDTO newUserOrderDTO) throws CreateDataFailException;

    public Boolean updateStatusToFinish(Long orderId) throws UpdateDataFailException;

    public Boolean updateStatusToCancel(Long orderId) throws UpdateDataFailException;

    public Boolean updateStatusToConfirm(Long orderId) throws UpdateDataFailException;

    public Boolean updateStatusToProcess(Long orderId) throws UpdateDataFailException;

    public Long getStatusOfOrder(Long orderID) throws DataNotFoundException;

}