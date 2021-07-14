package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.PaymentMethodRepository;
import nashtech.phucldh.ecommerce.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	PaymentMethodRepository paymentmethodRepository;

	@Override
	public List<PaymentMethod> findAll() {
		List<PaymentMethod> theListPaymentmethod = paymentmethodRepository.findAll();
		return theListPaymentmethod;
	}

	@Override
	public PaymentMethod getPaymentmethodById(Integer idPayment) throws DataNotFoundException{
		Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
		PaymentMethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return thePaymentmethod;
	}

}
