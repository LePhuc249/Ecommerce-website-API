package nashtech.phucldh.ecommerce.service;

import java.util.List;
import nashtech.phucldh.ecommerce.dto.AccountOrder.AccountOrderDTO;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface AccountOrderService {

    List<AccountOrder> findAll() throws DataNotFoundException;

    AccountOrder getAccountOrder(Long accountId, int totalPrice) throws DataNotFoundException;

    Boolean createNewOrder(AccountOrderDTO newUserOrderDTO) throws CreateDataFailException;

    Boolean updateStatusToFinish(Long orderId) throws UpdateDataFailException;

    Boolean updateStatusToCancel(Long orderId) throws UpdateDataFailException;

    Boolean updateStatusToConfirm(Long orderId) throws UpdateDataFailException;

    Boolean updateStatusToProcess(Long orderId) throws UpdateDataFailException;

    Long getStatusOfOrder(Long orderID) throws DataNotFoundException;

}