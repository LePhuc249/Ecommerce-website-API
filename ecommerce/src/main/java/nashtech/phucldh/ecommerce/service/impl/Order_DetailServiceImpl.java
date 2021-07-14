package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Order_Detail;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.Order_DetailRepository;
import nashtech.phucldh.ecommerce.service.Order_DetailService;

@Service
public class Order_DetailServiceImpl implements Order_DetailService {

	@Autowired
	Order_DetailRepository orderdetailrepository;

	@Override
	public List<Order_Detail> findAll() {
		List<Order_Detail> theListOrderdetail = orderdetailrepository.findAll();
		return theListOrderdetail;
	}

	@Override
	public List<String> getListItemProperty(String orderID) {
		List<String> theListProperty = orderdetailrepository.getListItemProperty(orderID);
		return theListProperty;
	}

	@Override
	public Order_Detail getOrderdetailByCode(Integer id) throws DataNotFoundException {
		Optional<Order_Detail> result = orderdetailrepository.findById(id);
		Order_Detail theOrderdetail = null;
		if (result.isPresent()) {
			theOrderdetail = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theOrderdetail;
	}

	@Override
	public void createOrderdetail(Order_Detail theOrderdetail) {
		orderdetailrepository.save(theOrderdetail);
	}

}
