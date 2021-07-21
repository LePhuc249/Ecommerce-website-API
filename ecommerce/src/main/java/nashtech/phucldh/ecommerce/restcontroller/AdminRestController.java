package nashtech.phucldh.ecommerce.restcontroller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;
import nashtech.phucldh.ecommerce.dto.OrganizationDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AccountService accountService;

    public ResponseEntity<ResponseDTO> lockAccount(@PathVariable int accountID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = false;
            accountService.deleteAccount(Long.valueOf(accountID));
            Long status = accountService.getStatusAccount(Long.valueOf(accountID));
            if (status.intValue() == 3) {
                result = true;
            }
            if (result) {
                response.setData(null);
                response.setSuccessCode(SuccessCode.ACCOUNT_DELETE_SUCCESS);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> activeAccount(@PathVariable int accountID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = false;
            accountService.activeAccount(Long.valueOf(accountID));
            Long status = accountService.getStatusAccount(Long.valueOf(accountID));
            if (status.intValue() == 2) {
                result = true;
            }
            if (result) {
                response.setData(null);
                response.setSuccessCode(SuccessCode.ACCOUNT_ACTIVE_SUCCESS);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAllAccount() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Account> list = accountService.getAllAccount();
            response.setData(list);
            response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAccountDetail(@PathVariable int accountID) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long value = Long.valueOf(String.valueOf(accountID));
            Account account = accountService.getAccountDetail(value);
            response.setData(account);
            response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }
}