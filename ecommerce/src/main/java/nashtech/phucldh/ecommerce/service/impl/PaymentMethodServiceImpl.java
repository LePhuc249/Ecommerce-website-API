package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.PaymentMethodConverter;
import nashtech.phucldh.ecommerce.dto.PaymentMethod.PaymentMethodDTO;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.PaymentMethodRepository;
import nashtech.phucldh.ecommerce.service.PaymentMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

    @Autowired
    PaymentMethodRepository paymentmethodRepository;

    @Autowired
    PaymentMethodConverter paymentMethodConverter;

    @Override
    public List<PaymentMethodDTO> findAllPayment() throws DataNotFoundException {
        List<PaymentMethodDTO> listDTO;
        try {
            List<PaymentMethod> theListPaymentmethod = paymentmethodRepository.findAll();
            if (theListPaymentmethod.size() > 0) {
                listDTO = new ArrayList<>();
                for (PaymentMethod payment: theListPaymentmethod) {
                    PaymentMethodDTO dto = paymentMethodConverter.convertPaymentMethodToDTO(payment);
                    listDTO.add(dto);
                }
            } else {
                LOGGER.info("The list payment method is empty ");
                throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_LIST_EMPTY);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when load list payment method: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public PaymentMethodDTO getPaymentmethodById(Long idPayment) throws DataNotFoundException {
        PaymentMethodDTO dto;
        try {
            Optional<PaymentMethod> result = paymentmethodRepository.findById(idPayment);
            PaymentMethod thePaymentmethod;
            if (result.isPresent()) {
                thePaymentmethod = result.get();
            } else {
                LOGGER.info("Can't find payment method " + idPayment);
                throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
            }
            dto = paymentMethodConverter.convertPaymentMethodToDTO(thePaymentmethod);
        } catch (Exception e) {
            LOGGER.info("Having error when load payment method: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_LOADED_FAIL);
        }
        return dto;
    }

    @Override
    public Boolean createNewPaymentMethod(PaymentMethodDTO dto) throws CreateDataFailException {
        boolean result;
        try {
            PaymentMethod tempPaymentMethod = paymentmethodRepository.checkExistedPaymentMethod(dto.getName());
            if (tempPaymentMethod != null) {
                LOGGER.info("Payment method have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_PAYMENT_METHOD_EXISTED);
            }
            PaymentMethod paymentMethod = paymentMethodConverter.convertPaymentMethodDTOToEntity(dto);
            paymentMethod.setCreateDate(LocalDateTime.now());
            paymentmethodRepository.save(paymentMethod);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create new payment method: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updatePaymentMethod(PaymentMethodDTO dto) throws UpdateDataFailException {
        boolean result;
        try {
            Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(dto.getId());
            if (!optionalPaymentMethod.isPresent()) {
                LOGGER.info("Can't find payment method by id " + dto.getId());
                throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
            }
            PaymentMethod paymentMethod = paymentMethodConverter.convertPaymentMethodDTOToEntity(dto);
            paymentMethod.setCreateDate(optionalPaymentMethod.get().getCreateDate());
            paymentmethodRepository.save(paymentMethod);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create new payment method: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deletePayment(Long idPayment) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        try {
            Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(idPayment);
            PaymentMethod thePaymentmethod;
            if (optionalPaymentMethod.isPresent()) {
                thePaymentmethod = optionalPaymentMethod.get();
            } else {
                LOGGER.info("Can't find payment method " + idPayment);
                throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
            }
            paymentmethodRepository.deletePayment(thePaymentmethod.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete payment method " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

    @Override
    public Boolean unDeletePayment(Long idPayment) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Optional<PaymentMethod> optionalPaymentMethod = paymentmethodRepository.findById(idPayment);
            PaymentMethod thePaymentmethod;
            if (optionalPaymentMethod.isPresent()) {
                thePaymentmethod = optionalPaymentMethod.get();
            } else {
                LOGGER.info("Can't find payment method " + idPayment);
                throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
            }
            paymentmethodRepository.unDeletePayment(thePaymentmethod.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update payment method " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return result;
    }

}