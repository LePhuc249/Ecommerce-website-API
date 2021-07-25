package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.OrderStatus;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface OrderStatusService {

    public List<OrderStatus> findAllStatus() throws DataNotFoundException;

    public OrderStatus getOrderStatusById(Long idStatus) throws DataNotFoundException;

    public Boolean deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException;

    public Boolean unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException;

    public Boolean updateNameOrderStatus(Long idStatus, String newName) throws DataNotFoundException, UpdateDataFailException;

}