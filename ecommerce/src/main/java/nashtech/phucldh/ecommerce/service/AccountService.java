package nashtech.phucldh.ecommerce.service;

import javax.security.auth.login.AccountNotFoundException;

import nashtech.phucldh.ecommerce.dto.Account.AccountProfileDTO;
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
import java.util.List;

public interface AccountService {

    Account getAccountDetail(Long accountId) throws DataNotFoundException;

    Account getAccountByUsername(String username) throws AccountNotFoundException;

    Boolean updateAccount(Account theAccount) throws UpdateDataFailException;

    Boolean deleteAccount(Long id) throws AccountNotFoundException, DeleteDataFailException;

    Boolean activeAccount(Long id) throws DeleteDataFailException, AccountNotFoundException, UpdateDataFailException;

    JwtResponse authenticateAccount(LoginRequest loginRequest) throws AccountAuthenticationException;

    Boolean registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException, DuplicateDataException, DataNotFoundException;

    Account getForgotAccount(String username, String fullname, String email, String phone);

    AccountProfileDTO getAccountToShow(Long accountId) throws DataNotFoundException;

    List<AccountProfileDTO> getListAccountProfilesForAdmin(int pageNo, String valueSort);

}