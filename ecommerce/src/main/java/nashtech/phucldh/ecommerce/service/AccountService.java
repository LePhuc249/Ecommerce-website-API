package nashtech.phucldh.ecommerce.service;

import javax.security.auth.login.AccountNotFoundException;

import nashtech.phucldh.ecommerce.entity.Account;

import nashtech.phucldh.ecommerce.exception.AccountAuthenticationException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;

import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;
import nashtech.phucldh.ecommerce.payload.response.JwtResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    public List<Account> getAllAccount() throws DataNotFoundException;

    public Account getAccountDetail(Long accountId) throws DataNotFoundException;

    public Account getAccountByEmail(String email) throws AccountNotFoundException;

    public Account getAccountByUsername(String username) throws AccountNotFoundException;

    public Boolean updateAccount(Account theAccount) throws UpdateDataFailException;

    public Boolean deleteAccount(Long id) throws AccountNotFoundException, DeleteDataFailException;

    public Boolean activeAccount(Long id) throws DeleteDataFailException, AccountNotFoundException, UpdateDataFailException;

    public JwtResponse authenticateAccount(LoginRequest loginRequest) throws AccountAuthenticationException;

    public Boolean registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException, DuplicateDataException, DataNotFoundException;

    public Long getStatusAccount(Long id) throws AccountNotFoundException;

    public Account getForgotAccount(String username, String fullname, String email, String phone);

    public Page<Account> getPaginationAccount(int pageNo, String valueSort);

}