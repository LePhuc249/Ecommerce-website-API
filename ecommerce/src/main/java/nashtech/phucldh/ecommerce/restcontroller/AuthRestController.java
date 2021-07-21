package nashtech.phucldh.ecommerce.restcontroller;

import javax.validation.Valid;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.exception.AccountAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;
import nashtech.phucldh.ecommerce.service.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) throws AccountAuthenticationException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountService.authenticateAccount(loginRequest);
            response.setSuccessCode(SuccessCode.USER_LOGIN_SUCCESS);
            response.setData(result);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_LOGIN_FAIL);
            throw new AccountAuthenticationException(ErrorCode.ERR_ACCOUNT_LOGIN_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerCustomer(@Valid @RequestBody SignUpRequest signUpRequest) throws DataNotFoundException, AccountAuthenticationException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = accountService.registerAccount(signUpRequest);
            response.setSuccessCode(SuccessCode.USER_SIGNUP_SUCCESS);
            response.setData(result);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ACCOUNT_FAIL);
            throw new AccountAuthenticationException(ErrorCode.ERR_CREATE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }
}