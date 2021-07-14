package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Payment_Method;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface Payment_MethodService {
	
	public List<Payment_Method> findAll();
	
	public Payment_Method getPaymentmethodById(Integer idPayment) throws DataNotFoundException;
}
