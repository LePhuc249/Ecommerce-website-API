package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;
import nashtech.phucldh.ecommerce.dto.Account.AccountProfileDTO;
import nashtech.phucldh.ecommerce.dto.AccountStatus.AccountStatusDTO;
import nashtech.phucldh.ecommerce.dto.OrderStatus.OrderStatusDTO;
import nashtech.phucldh.ecommerce.dto.PaymentMethod.PaymentMethodDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.service.AccountStatusService;
import nashtech.phucldh.ecommerce.service.OrderStatusService;
import nashtech.phucldh.ecommerce.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private AccountStatusService accountStatusService;

    @GetMapping("/account/{page}")
    public ResponseEntity<ResponseDTO> getAllAccount(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<AccountProfileDTO> listDTO = accountService.getListAccountProfilesForAdmin(pageNo, "fullName");
            if (listDTO.size() > 0) {
                response.setData(listDTO);
                response.setSuccessCode(SuccessCode.ACCOUNT_LIST_LOADED_SUCCESS);
            } else {
                response.setData(false);
                response.setSuccessCode(ErrorCode.ERR_ACCOUNT_LIST_EMPTY);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/account/details/{accountID}")
    public ResponseEntity<ResponseDTO> getAccountDetail(@PathVariable int accountID) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long value = Long.valueOf(String.valueOf(accountID));
            AccountProfileDTO dto = accountService.getAccountToShow(value);
            if (dto != null) {
                response.setData(dto);
                response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/account/lockAccount/{accountID}")
    public ResponseEntity<ResponseDTO> lockAccount(@PathVariable("accountID") int accountID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountService.deleteAccount(Long.valueOf(String.valueOf(accountID)));
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/account/unlockAccount/{accountID}")
    public ResponseEntity<ResponseDTO> activeAccount(@PathVariable("accountID") int accountID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountService.activeAccount(Long.valueOf(String.valueOf(accountID)));
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_ACTIVE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/orderstatus")
    public ResponseEntity<ResponseDTO> createNewOrderStatus(@RequestBody OrderStatusDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = orderStatusService.createNewOrderStatus(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_STATUS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ORDER_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORDER_STATUS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDER_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/orderstatus")
    public ResponseEntity<ResponseDTO> updateOrderStatus(@RequestBody OrderStatusDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = orderStatusService.updateOrderStatus(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_STATUS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/orderstatus/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderStatus(@PathVariable("id") int id) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long statusId = Long.valueOf(String.valueOf(id));
            boolean result = orderStatusService.deleteOrderStatus(statusId);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_STATUS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORDER_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/orderstatus/undelete/{id}")
    public ResponseEntity<ResponseDTO> activeOrderStatus(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long statusId = Long.valueOf(String.valueOf(id));
            boolean result = orderStatusService.unDeleteOrderStatus(statusId);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_STATUS_ACTIVE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORDER_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orderstatus/{id}")
    public ResponseEntity<ResponseDTO> findOrderStatus(@PathVariable("id") int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long statusId = Long.valueOf(String.valueOf(id));
            OrderStatusDTO dto = orderStatusService.getOrderStatusById(statusId);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.ORDER_STATUS_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ORDER_STATUS_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orderstatus")
    public ResponseEntity<ResponseDTO> findAllOrderStatus() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<OrderStatusDTO> list = orderStatusService.findAllStatus();
            response.setData(list);
            response.setSuccessCode(SuccessCode.ORDER_STATUS_LIST_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ORDER_STATUS_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/paymentmethod")
    public ResponseEntity<ResponseDTO> createNewPaymentMethod(@RequestBody PaymentMethodDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = paymentMethodService.createNewPaymentMethod(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PAYMENT_METHOD_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_PAYMENT_METHOD_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_PAYMENT_METHOD_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PAYMENT_METHOD_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/paymentmethod")
    public ResponseEntity<ResponseDTO> updatePaymentMethod(@RequestBody PaymentMethodDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = paymentMethodService.updatePaymentMethod(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PAYMENT_METHOD_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/paymentmethod/delete/{id}")
    public ResponseEntity<ResponseDTO> deletePaymentMethod(@PathVariable("id") int id) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long paymentId = Long.valueOf(String.valueOf(id));
            boolean result = paymentMethodService.deletePayment(paymentId);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PAYMENT_METHOD_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PAYMENT_METHOD_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/paymentmethod/undelete/{id}")
    public ResponseEntity<ResponseDTO> activePaymentMethod(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long paymentId = Long.valueOf(String.valueOf(id));
            boolean result = paymentMethodService.unDeletePayment(paymentId);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PAYMENT_METHOD_ACTIVE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PAYMENT_METHOD_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/paymentmethod/{id}")
    public ResponseEntity<ResponseDTO> findPaymentMethod(@PathVariable("id") int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long paymentId = Long.valueOf(String.valueOf(id));
            PaymentMethodDTO payment = paymentMethodService.getPaymentmethodById(paymentId);
            response.setData(payment);
            response.setSuccessCode(SuccessCode.PAYMENT_METHOD_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PAYMENT_METHOD_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/paymentmethod")
    public ResponseEntity<ResponseDTO> findAllPaymentMethod() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<PaymentMethodDTO> list = paymentMethodService.findAllPayment();
            response.setData(list);
            response.setSuccessCode(SuccessCode.PAYMENT_METHOD_LIST_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PAYMENT_METHOD_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/accountstatus")
    public ResponseEntity<ResponseDTO> createNewAccountStatus(@RequestBody AccountStatusDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountStatusService.createNewAccountStatus(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_STATUS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ACCOUNT_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ACCOUNT_STATUS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accountstatus")
    public ResponseEntity<ResponseDTO> updateAccountStatus(@RequestBody AccountStatusDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountStatusService.updateAccountStatus(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_STATUS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_STATUS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/accountstatus/{id}")
    public ResponseEntity<ResponseDTO> deleteAccountStatus(@PathVariable("id") int id) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long paymentId = Long.valueOf(String.valueOf(id));
            boolean result = accountStatusService.deleteAccountStatus(paymentId);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_STATUS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_STATUS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_STATUS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_STATUS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/accountstatus")
    public ResponseEntity<ResponseDTO> findAllAccountStatus() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<AccountStatusDTO> list = accountStatusService.getAllStatus();
            response.setData(list);
            response.setSuccessCode(SuccessCode.ACCOUNT_STATUS_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_STATUS_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

}