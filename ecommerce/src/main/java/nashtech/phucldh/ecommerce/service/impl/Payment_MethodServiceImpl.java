package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Payment_Method;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.Payment_MethodRepository;
import nashtech.phucldh.ecommerce.service.Payment_MethodService;

@Service
public class Payment_MethodServiceImpl implements Payment_MethodService {

	@Autowired
	Payment_MethodRepository paymentmethodRepository;

	@Override
	public List<Payment_Method> findAll() {
		List<Payment_Method> theListPaymentmethod = paymentmethodRepository.findAll();
		return theListPaymentmethod;
	}

	@Override
	public Payment_Method getPaymentmethodById(Integer idPayment) throws DataNotFoundException{
		Optional<Payment_Method> result = paymentmethodRepository.findById(idPayment);
		Payment_Method thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return thePaymentmethod;
	}

}
