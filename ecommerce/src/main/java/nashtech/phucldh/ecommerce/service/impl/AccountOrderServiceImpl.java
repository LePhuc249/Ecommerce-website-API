package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.AccountOrderConverter;
import nashtech.phucldh.ecommerce.dto.AccountOrder.AccountOrderDTO;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.AccountOrderRepository;
import nashtech.phucldh.ecommerce.service.AccountOrderService;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOrderServiceImpl.class);

    @Autowired
    AccountOrderRepository userorderRepository;

    @Autowired
    AccountOrderConverter accountOrderConverter;

    @Override
    public List<AccountOrder> findAll() throws DataNotFoundException {
        List<AccountOrder> theListUserorder;
        try {
            theListUserorder = userorderRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Having error when load the list order: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_LIST_LOADED_FAIL);
        }
        return theListUserorder;
    }

    @Override
    public Boolean createNewOrder(AccountOrderDTO newUserOrderDTO) throws CreateDataFailException {
        boolean result;
        try {
            AccountOrder order = accountOrderConverter.convertAccountOrderToEntity(newUserOrderDTO);
            userorderRepository.save(order);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create a new order: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL);
        }
        return result;
    }

    @Override
    public AccountOrder getAccountOrder(Long accountId, int totalPrice) throws DataNotFoundException {
        return userorderRepository.getAccountOrderByAccountAndTotalPrice(accountId, totalPrice);
    }

    @Override
    public Boolean updateStatusToFinish(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value;
        try {
            value = userorderRepository.updateStatusToFinish(orderId);
        } catch (Exception e) {
            LOGGER.info("Having error when update status order " + orderId + " To Finish: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean updateStatusToCancel(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value;
        try {
            value = userorderRepository.updateStatusToCancel(orderId);
        } catch (Exception e) {
            LOGGER.info("Having error when update status order " + orderId + " To Cancel: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean updateStatusToConfirm(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value;
        try {
            value = userorderRepository.updateStatusToConfirm(orderId);
        } catch (Exception e) {
            LOGGER.info("Having error when update status order " + orderId + " To Confirm: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean updateStatusToProcess(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value;
        try {
            value = userorderRepository.updateStatusToProcessing(orderId);
        } catch (Exception e) {
            LOGGER.info("Having error when update status order " + orderId + " To Process: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Long getStatusOfOrder(Long orderID) throws DataNotFoundException {
        AccountOrder order;
        Optional<AccountOrder> orderOptional = userorderRepository.findById(orderID);
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            LOGGER.info("Can't get status order " + orderID);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
        }
        return order.getStatus();
    }

}