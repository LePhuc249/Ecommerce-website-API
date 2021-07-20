package nashtech.phucldh.ecommerce.service;

import javax.security.auth.login.AccountNotFoundException;

import nashtech.phucldh.ecommerce.exception.AccountAuthenticationException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.http.ResponseEntity;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;

public interface AccountService {

    public ResponseEntity<?> authenticateAccount(LoginRequest loginRequest) throws AccountAuthenticationException;

    public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException, DuplicateDataException, DataNotFoundException;

    public Account getAccountByEmail(String email) throws AccountNotFoundException;

    public Account getAccountByUsername(String username) throws AccountNotFoundException;

    public void updateAccount(Account theAccount) throws UpdateDataFailException;

    public void deleteAccount(Long id) throws AccountNotFoundException, DeleteDataFailException;

    public void activeAccount(Long id) throws DeleteDataFailException, AccountNotFoundException, UpdateDataFailException;

    public Long getStatusAccount(Long id) throws AccountNotFoundException;

}