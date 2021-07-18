package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.OrderStatusRepository;
import nashtech.phucldh.ecommerce.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAllStatus() throws DataNotFoundException {
        List<OrderStatus> theListOrderStatus = null;
        try {
            theListOrderStatus = orderStatusRepository.findAll();
        } catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        return theListOrderStatus;
    }

    @Override
    public OrderStatus getOrderStatusById(Long idStatus) throws DataNotFoundException {
        Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus = null;
        if (result.isPresent()) {
            theOrderStatus = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        return theOrderStatus;
    }

    @Override
    public void deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException {
        Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus = null;
        if (result.isPresent()) {
            theOrderStatus = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.deleteOrderStatus(theOrderStatus.getId());
        } catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
        }
    }

    @Override
    public void unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException {
        Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus = null;
        if (result.isPresent()) {
            theOrderStatus = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.unDeleteOrderStatus(theOrderStatus.getId());
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
    }

    @Override
    public void updateNameOrderStatus(Long idStatus, String newName) throws DataNotFoundException, UpdateDataFailException {
        Optional<OrderStatus> result = orderStatusRepository.findById(idStatus);
        OrderStatus theOrderStatus = null;
        if (result.isPresent()) {
            theOrderStatus = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        try {
            orderStatusRepository.updateNameOrderStatus(theOrderStatus.getId(), newName);
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
    }
}
