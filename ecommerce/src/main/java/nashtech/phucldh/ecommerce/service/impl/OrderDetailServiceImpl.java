package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.OrderDetail;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.OrderDetailRepository;

import nashtech.phucldh.ecommerce.service.OrderDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    OrderDetailRepository orderdetailrepository;

    @Override
    public List<OrderDetail> findAllItem() throws DataNotFoundException {
        List<OrderDetail> theListOrderdetail;
        try {
            theListOrderdetail = orderdetailrepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find all item ");
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
        }
        return theListOrderdetail;
    }

    @Override
    public List<String> getListItemProperty(Long orderID) throws DataNotFoundException {
        List<String> theListProperty;
        try {
            theListProperty = orderdetailrepository.getListItemProperty(orderID);
        } catch (Exception ex) {
            LOGGER.info("Can't find item property ");
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
        }
        return theListProperty;
    }

    @Override
    public OrderDetail getOrderdetailByCode(Long id) throws DataNotFoundException {
        Optional<OrderDetail> result = orderdetailrepository.findById(id);
        OrderDetail theOrderdetail;
        if (result.isPresent()) {
            theOrderdetail = result.get();
        } else {
            LOGGER.info("Can't find order detail by code " + id);
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        return theOrderdetail;
    }

    @Override
    public Boolean createOrderdetail(OrderDetail theOrderdetail) throws CreateDataFailException {
        boolean result;
        try {
            orderdetailrepository.save(theOrderdetail);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't create new order details ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
        }
        return result;
    }

    @Override
    public List<OrderDetail> getListItemInOrder(Long orderId) throws DataNotFoundException {
        List<OrderDetail> listDetail = null;
        try {
            listDetail = orderdetailrepository.getListItemInOrder(orderId);
        } catch (Exception ex) {
            LOGGER.info("Can't find item in order by id " + orderId);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
        }
        return null;
    }

    @Override
    public Boolean updateQuantity(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<OrderDetail> detailOptional = orderdetailrepository.findById(id);
        OrderDetail detail;
        if (detailOptional.isPresent()) {
            detail = detailOptional.get();
        } else {
            LOGGER.info("Can't find detail by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
        }
        int amount;
        amount = orderdetailrepository.updateQuantityItem(detail.getId(), quantity);
        result = true;
        if (amount == 0) {
            LOGGER.info("Can't update quantity of item in order ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_DETAIL_FAIL);
        }
        return result;
    }

}