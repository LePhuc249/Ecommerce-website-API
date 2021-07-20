package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.AccountOrderRepository;
import nashtech.phucldh.ecommerce.service.AccountOrderService;

@Service
public class AccountOrderServiceImpl implements AccountOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOrderServiceImpl.class);

    @Autowired
    AccountOrderRepository userorderRepository;

    @Override
    public List<AccountOrder> findAll() throws DataNotFoundException {
        List<AccountOrder> theListUserorder = null;
        try {
            theListUserorder = userorderRepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find all order");
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
        }
        return theListUserorder;
    }

    @Override
    public List<AccountOrder> findOrderOfCustomer(Long accountID) throws DataNotFoundException {
        List<AccountOrder> result = null;
        try {
            result = userorderRepository.findByCustomerid(accountID);
        } catch (Exception ex) {
            LOGGER.info("Can't find all order of Customer id: " + accountID);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<String> listUsedCoupons(Long id) throws DataNotFoundException {
        List<String> result = null;
        try {
            result = userorderRepository.findByCouponid(id);
        } catch (Exception ex) {
            LOGGER.info("Can't find the list coupons have been used");
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
        }
        return result;
    }

    @Override
    public void createNewOrder(AccountOrder newUserOrder) throws CreateDataFailException {
        try {
            userorderRepository.save(newUserOrder);
        } catch (Exception ex) {
            LOGGER.info("Can't create a new order");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL);
        }
    }

    @Override
    public AccountOrder getOrderById(Long orderID) throws DataNotFoundException {
        Optional<AccountOrder> result = userorderRepository.findById(orderID);
        AccountOrder theUserorder = null;
        if (result.isPresent()) {
            theUserorder = result.get();
        } else {
            LOGGER.info("Can't find order by the id " + orderID);
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        return theUserorder;
    }

    @Override
    public boolean updateStatusToFinish(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value = 0;
        try {
            value = userorderRepository.updateStatusToFinish(orderId);
        } catch (Exception ex) {
            LOGGER.info("Can't update status order " + orderId + " To Finish");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateStatusToCancel(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value = 0;
        try {
            value = userorderRepository.updateStatusToCancel(orderId);
        } catch (Exception ex) {
            LOGGER.info("Can't update status order " + orderId + " To Cancel");
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateStatusToConfirm(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value = 0;
        try {
            value = userorderRepository.updateStatusToConfirm(orderId);
        } catch (Exception ex) {
            LOGGER.info("Can't update status order " + orderId + " To Confirm");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateStatusToProcess(Long orderId) throws UpdateDataFailException {
        boolean result = false;
        int value = 0;
        try {
            value = userorderRepository.updateStatusToProcessing(orderId);
        } catch (Exception ex) {
            LOGGER.info("Can't update status order " + orderId + " To Process");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        if (value > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Long getStatusOfOrder(Long orderID) throws DataNotFoundException {
        AccountOrder order = null;
        Optional<AccountOrder> orderOptional = userorderRepository.findById(orderID);
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            LOGGER.info("Can't get status order " + orderID);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
        }
        Long status = order.getStatus();
        return status;
    }

}