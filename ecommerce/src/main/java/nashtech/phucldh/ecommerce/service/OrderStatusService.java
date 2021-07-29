package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.OrderStatus.OrderStatusDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface OrderStatusService {

    List<OrderStatusDTO> findAllStatus() throws DataNotFoundException;

    OrderStatusDTO getOrderStatusById(Long idStatus) throws DataNotFoundException;

    Boolean createNewOrderStatus(OrderStatusDTO dto) throws CreateDataFailException;

    Boolean updateOrderStatus(OrderStatusDTO dto) throws UpdateDataFailException;

    Boolean deleteOrderStatus(Long idStatus) throws DataNotFoundException, DeleteDataFailException;

    Boolean unDeleteOrderStatus(Long idStatus) throws DataNotFoundException, UpdateDataFailException;

}