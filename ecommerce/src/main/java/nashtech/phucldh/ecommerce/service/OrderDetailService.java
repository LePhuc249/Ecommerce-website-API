package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface OrderDetailService {
	
	public List<OrderDetail> findAll();
	
	public List<String> getListItemProperty(String orderID);
	
	public OrderDetail getOrderdetailByCode(Integer id) throws DataNotFoundException;
	
	public void createOrderdetail(OrderDetail theCoupon);
	
}
