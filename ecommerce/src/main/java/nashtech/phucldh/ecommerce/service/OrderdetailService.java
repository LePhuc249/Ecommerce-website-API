package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Order_Detail;

public interface OrderdetailService {
	
	public List<Order_Detail> findAll();
	
	public List<String> getListItemProperty(String orderID);
	
	public Order_Detail getOrderdetailByCode(String code);
	
	public void createOrderdetail(Order_Detail theCoupon);
	
}
