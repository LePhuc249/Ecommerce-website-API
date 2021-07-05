package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Paymentmethod;

public interface PaymentmethodService {
	
	public List<Paymentmethod> findAll();
	
	public Paymentmethod getPaymentmethodById(String idPayment);
}
