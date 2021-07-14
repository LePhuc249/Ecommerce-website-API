package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.OrderDetailRepository;
import nashtech.phucldh.ecommerce.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailRepository orderdetailrepository;

	@Override
	public List<OrderDetail> findAll() {
		List<OrderDetail> theListOrderdetail = orderdetailrepository.findAll();
		return theListOrderdetail;
	}

	@Override
	public List<String> getListItemProperty(String orderID) {
		List<String> theListProperty = orderdetailrepository.getListItemProperty(orderID);
		return theListProperty;
	}

	@Override
	public OrderDetail getOrderdetailByCode(Integer id) throws DataNotFoundException {
		Optional<OrderDetail> result = orderdetailrepository.findById(id);
		OrderDetail theOrderdetail = null;
		if (result.isPresent()) {
			theOrderdetail = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theOrderdetail;
	}

	@Override
	public void createOrderdetail(OrderDetail theOrderdetail) {
		orderdetailrepository.save(theOrderdetail);
	}

}
