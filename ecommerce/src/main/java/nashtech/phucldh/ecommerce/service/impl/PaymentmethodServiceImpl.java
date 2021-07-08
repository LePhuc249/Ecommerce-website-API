package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Paymentmethod;
import nashtech.phucldh.ecommerce.exception.PaymentmethodNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.PaymentmethodRepository;
import nashtech.phucldh.ecommerce.service.PaymentmethodService;

@Service
public class PaymentmethodServiceImpl implements PaymentmethodService{
	
	@Autowired
	PaymentmethodRepository paymentmethodRepository;

	@Override
	public List<Paymentmethod> findAll() {
		List<Paymentmethod> theListPaymentmethod = paymentmethodRepository.findAll();
		return theListPaymentmethod;
	}

	@Override
	public Paymentmethod getPaymentmethodById(String idPayment) {
		Optional<Paymentmethod> result = paymentmethodRepository.findById(idPayment);
		Paymentmethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new PaymentmethodNotFoundException("Did not find payment method by code - " + idPayment);
		}
		return thePaymentmethod;
	}

}
