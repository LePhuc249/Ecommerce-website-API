package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Orderdetail;

public interface OrderdetailService {
	
	public List<Orderdetail> findAll();
	
	public List<String> getListItemProperty(String orderID);
	
	public Orderdetail getOrderdetailByCode(String code);
	
	public void createOrderdetail(Orderdetail theCoupon);
	
}
