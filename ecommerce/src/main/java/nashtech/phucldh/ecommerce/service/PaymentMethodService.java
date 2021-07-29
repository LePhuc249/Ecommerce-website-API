package nashtech.phucldh.ecommerce.service;

import java.util.List;
import nashtech.phucldh.ecommerce.dto.PaymentMethod.PaymentMethodDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface PaymentMethodService {

    List<PaymentMethodDTO> findAllPayment() throws DataNotFoundException;

    PaymentMethodDTO getPaymentmethodById(Long idPayment) throws DataNotFoundException;

    Boolean createNewPaymentMethod(PaymentMethodDTO dto) throws CreateDataFailException;

    Boolean updatePaymentMethod(PaymentMethodDTO dto) throws UpdateDataFailException;

    Boolean deletePayment(Long idPayment) throws DataNotFoundException, DeleteDataFailException;

    Boolean unDeletePayment(Long idPayment) throws DataNotFoundException, UpdateDataFailException;

}