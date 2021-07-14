package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Payment_Method;

public interface PaymentmethodService {
	
	public List<Payment_Method> findAll();
	
	public Payment_Method getPaymentmethodById(String idPayment);
}
