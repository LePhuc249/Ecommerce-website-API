package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Order_Detail;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface Order_DetailService {
	
	public List<Order_Detail> findAll();
	
	public List<String> getListItemProperty(String orderID);
	
	public Order_Detail getOrderdetailByCode(Integer id) throws DataNotFoundException;
	
	public void createOrderdetail(Order_Detail theCoupon);
	
}
