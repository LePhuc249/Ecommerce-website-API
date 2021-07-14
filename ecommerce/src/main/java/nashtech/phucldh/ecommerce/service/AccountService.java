package nashtech.phucldh.ecommerce.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;

public interface AccountService {
	
	public ResponseEntity<?> authenticateAccount(LoginRequest loginRequest);

    public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException, DuplicateDataException, DataNotFoundException;
    
	public Account getAccountByEmail(String email) throws AccountNotFoundException;

	public void updateStatus(Account theAccount);

	public void deleteAccount(String username);
}
