package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.OrderStatus;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.OrderStatusRepository;

import nashtech.phucldh.ecommerce.service.OrderStatusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAllStatus() throws DataNotFoundException {
        List<OrderStatus> theListOrderStatus;
        try {
            theListOrderStatus = orderStatusRepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find all status ");
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        return theListOrderStatus;
    }

    @Override
    public OrderStatus getOrderStatusById(Long idStatus) throws DataNotFoundException {
        Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus;
        if (result.isPresent()) {
            theOrderStatus = result.get();
        } else {
            LOGGER.info("Can't find status by id " + idStatus);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        return theOrderStatus;
    }

    @Override
    public Boolean deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus;
        if (optionalOrderStatus.isPresent()) {
            theOrderStatus = optionalOrderStatus.get();
        } else {
            LOGGER.info("Can't find status by id " + idStatus);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.deleteOrderStatus(theOrderStatus.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't delete status by id " + idStatus);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus;
        if (optionalOrderStatus.isPresent()) {
            theOrderStatus = optionalOrderStatus.get();
        } else {
            LOGGER.info("Can't find status by id " + idStatus);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.unDeleteOrderStatus(theOrderStatus.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update status by id " + idStatus);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateNameOrderStatus(Long idStatus, String newName) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus;
        if (optionalOrderStatus.isPresent()) {
            theOrderStatus = optionalOrderStatus.get();
        } else {
            LOGGER.info("Can't find status by id " + idStatus);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.updateNameOrderStatus(theOrderStatus.getId(), newName);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update status name by id " + idStatus);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return result;
    }

}