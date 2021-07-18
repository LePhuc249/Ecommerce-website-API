package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
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
	public List<PaymentMethod> findAllPayment() throws DataNotFoundException {
		List<PaymentMethod> theListPaymentmethod = null;
		try {
			theListPaymentmethod = paymentmethodRepository.findAll();
		} catch (Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
		}
		return theListPaymentmethod;
	}

	@Override
	public PaymentMethod getPaymentmethodById(Long idPayment) throws DataNotFoundException{
		Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
		PaymentMethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
		}
		return thePaymentmethod;
	}

	@Override
	public void deletePayment(Long idPayment) throws DataNotFoundException, DeleteDataFailException {
		Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
		PaymentMethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
		}
		try {
			paymentmethodRepository.deletePayment(thePaymentmethod.getId());
		} catch (Exception ex) {
			throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
		}
	}

	@Override
	public void unDeletePayment(Long idPayment) throws DataNotFoundException, UpdateDataFailException {
		Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
		PaymentMethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
		}
		try {
			paymentmethodRepository.unDeletePayment(thePaymentmethod.getId());
		} catch (Exception ex) {
			throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
		}
	}

	@Override
	public void updateNamePaymentMethod(Long idPayment, String newName) throws DataNotFoundException, UpdateDataFailException {
		Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
		PaymentMethod thePaymentmethod = null;
		if (result.isPresent()) {
			thePaymentmethod = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
		}
		try {
			paymentmethodRepository.updateNamePayment(thePaymentmethod.getId(), newName);
		} catch (Exception ex) {
			throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
		}
	}

}
