package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface PaymentMethodService {
	
	public List<PaymentMethod> findAll();
	
	public PaymentMethod getPaymentmethodById(Integer idPayment) throws DataNotFoundException;
}
