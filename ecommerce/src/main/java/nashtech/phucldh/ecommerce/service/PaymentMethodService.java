package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.PaymentMethod;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface PaymentMethodService {

    public List<PaymentMethod> findAllPayment() throws DataNotFoundException;

    public PaymentMethod getPaymentmethodById(Long idPayment) throws DataNotFoundException;

    public Boolean deletePayment(Long idPayment) throws DataNotFoundException, DeleteDataFailException;

    public Boolean unDeletePayment(Long idPayment) throws DataNotFoundException, UpdateDataFailException;

    public Boolean updateNamePaymentMethod(Long idPayment, String newName) throws DataNotFoundException, UpdateDataFailException;

}