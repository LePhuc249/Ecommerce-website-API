package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Orderdetail;
import nashtech.phucldh.ecommerce.reponsitory.OrderdetailRepository;
import nashtech.phucldh.ecommerce.service.OrderdetailService;

@Service
public class OrderdetailServiceImpl implements OrderdetailService {
	
	@Autowired
	OrderdetailRepository orderdetailrepository;

	@Override
	public List<Orderdetail> findAll() {
		List<Orderdetail> theListOrderdetail = orderdetailrepository.findAll();
		return theListOrderdetail;
	}

	@Override
	public List<String> getListItemProperty(String orderID) {
		List<String> theListProperty = orderdetailrepository.getListItemProperty(orderID);
		return theListProperty;
	}

	@Override
	public Orderdetail getOrderdetailByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOrderdetail(Orderdetail theCoupon) {
		// TODO Auto-generated method stub
		
	}

}
