package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.PaymentMethod;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.PaymentMethodRepository;

import nashtech.phucldh.ecommerce.service.PaymentMethodService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

    @Autowired
    PaymentMethodRepository paymentmethodRepository;

    @Override
    public List<PaymentMethod> findAllPayment() throws DataNotFoundException {
        List<PaymentMethod> theListPaymentmethod;
        try {
            theListPaymentmethod = paymentmethodRepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find all payment method ");
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        return theListPaymentmethod;
    }

    @Override
    public PaymentMethod getPaymentmethodById(Long idPayment) throws DataNotFoundException {
        Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
        PaymentMethod thePaymentmethod;
        if (result.isPresent()) {
            thePaymentmethod = result.get();
        } else {
            LOGGER.info("Can't find payment method " + idPayment);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        return thePaymentmethod;
    }

    @Override
    public Boolean deletePayment(Long idPayment) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(idPayment);
        PaymentMethod thePaymentmethod;
        if (optionalPaymentMethod.isPresent()) {
            thePaymentmethod = optionalPaymentMethod.get();
        } else {
            LOGGER.info("Can't find payment method " + idPayment);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        try {
            paymentmethodRepository.deletePayment(thePaymentmethod.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't delete payment method " + idPayment);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

    @Override
    public Boolean unDeletePayment(Long idPayment) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(idPayment);
        PaymentMethod thePaymentmethod;
        if (optionalPaymentMethod.isPresent()) {
            thePaymentmethod = optionalPaymentMethod.get();
        } else {
            LOGGER.info("Can't find payment method " + idPayment);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        try {
            paymentmethodRepository.unDeletePayment(thePaymentmethod.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update payment method " + idPayment);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateNamePaymentMethod(Long idPayment, String newName) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(idPayment);
        PaymentMethod thePaymentmethod;
        if (optionalPaymentMethod.isPresent()) {
            thePaymentmethod = optionalPaymentMethod.get();
        } else {
            LOGGER.info("Can't find payment method " + idPayment);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        try {
            paymentmethodRepository.updateNamePayment(thePaymentmethod.getId(), newName);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update name payment method " + idPayment);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

}