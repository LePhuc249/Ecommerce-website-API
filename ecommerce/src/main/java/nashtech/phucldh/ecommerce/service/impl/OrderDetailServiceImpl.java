package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
	public List<OrderDetail> findAllItem() throws DataNotFoundException {
		List<OrderDetail> theListOrderdetail = null;
		try {
			theListOrderdetail = orderdetailrepository.findAll();
		} catch (Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
		}
		return theListOrderdetail;
	}

	@Override
	public List<String> getListItemProperty(Long orderID) throws DataNotFoundException {
		List<String> theListProperty = null;
		try {
			theListProperty = orderdetailrepository.getListItemProperty(orderID);
		} catch (Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
		}
		return theListProperty;
	}

	@Override
	public OrderDetail getOrderdetailByCode(Long id) throws DataNotFoundException {
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
	public void createOrderdetail(OrderDetail theOrderdetail) throws CreateDataFailException {
		try {
			orderdetailrepository.save(theOrderdetail);
		} catch (Exception ex) {
			throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
		}
	}

	@Override
	public List<OrderDetail> getListItemInOrder(Long orderId) throws DataNotFoundException {
		List<OrderDetail> listDetail = null;
		try {
			listDetail = orderdetailrepository.getListItemInOrder(orderId);
		} catch (Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
		}
		return null;
	}

	@Override
	public void updateQuantity(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException {
		Optional<OrderDetail> detailOptional = orderdetailrepository.findById(id);
		OrderDetail detail = null;
		if (detailOptional.isPresent()) {
			detail = detailOptional.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ORDER_DETAIL_NOT_FOUND);
		}
		boolean result = false;
		int amount = 0;
		amount = orderdetailrepository.updateQuantityItem(detail.getId(), quantity);
		if (amount == 0) {
			throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_DETAIL_FAIL);
		}
	}

}
