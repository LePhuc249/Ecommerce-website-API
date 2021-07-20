package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface OrderStatusService {

    public List<OrderStatus> findAllStatus() throws DataNotFoundException;

    public OrderStatus getOrderStatusById(Long idStatus) throws DataNotFoundException;

    public void deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException;

    public void unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException;

    public void updateNameOrderStatus(Long idStatus, String newName) throws DataNotFoundException, UpdateDataFailException;

}