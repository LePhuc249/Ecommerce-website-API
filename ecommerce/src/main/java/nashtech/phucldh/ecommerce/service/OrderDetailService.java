package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface OrderDetailService {

    public List<OrderDetail> findAllItem() throws DataNotFoundException;

    public List<String> getListItemProperty(Long orderID) throws DataNotFoundException;

    public OrderDetail getOrderdetailByCode(Long id) throws DataNotFoundException;

    public void createOrderdetail(OrderDetail theCoupon) throws CreateDataFailException;

    public List<OrderDetail> getListItemInOrder(Long orderId) throws DataNotFoundException;

    public void updateQuantity(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException;

}