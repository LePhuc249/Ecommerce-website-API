package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.converter.AccountConverter;
import nashtech.phucldh.ecommerce.dto.Account.AccountProfileDTO;
import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;
import nashtech.phucldh.ecommerce.payload.response.JwtResponse;
import nashtech.phucldh.ecommerce.repository.AccountReponsitory;
import nashtech.phucldh.ecommerce.repository.RoleRepository;
import nashtech.phucldh.ecommerce.sercurity.jwt.JwtUtils;
import nashtech.phucldh.ecommerce.sercurity.service.impl.UserDetailsImpl;
import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.exception.AccountAuthenticationException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountReponsitory accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AccountConverter accountConverter;

    @Override
    public JwtResponse authenticateAccount(LoginRequest loginRequest) throws AccountAuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getFullname(), userDetails.getEmail(), roles);
        } catch (Exception e) {
            LOGGER.info("Account Authentication Error");
            throw new AccountAuthenticationException(ErrorCode.ERR_ACCOUNT_LOGIN_FAIL);
        }
    }

    @Override
    public Boolean registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException {
        boolean result;
        try {
            boolean existedUsername = accountRepository.existsByUserName(signUpRequest.getUsername());
            if (existedUsername) {
                LOGGER.info("Username " + signUpRequest.getUsername() + " is already taken");
                throw new DuplicateDataException(ErrorCode.ERR_USERNAME_ALREADY_TAKEN);
            }
            boolean existedEmail = accountRepository.existsByEmail(signUpRequest.getEmail());
            if (existedEmail) {
                LOGGER.info("Email " + signUpRequest.getEmail() + " is already taken");
                throw new DuplicateDataException(ErrorCode.ERR_EMAIL_ALREADY_TAKEN);
            }
            boolean existedPhone = accountRepository.existsByPhone(signUpRequest.getPhoneNumber());
            if (existedPhone) {
                LOGGER.info("Phone " + signUpRequest.getPhoneNumber() + " is already taken");
                throw new DuplicateDataException(ErrorCode.ERR_PHONE_ALREADY_TAKEN);
            }
            Account theAccount = new Account();
            theAccount.setUserName(signUpRequest.getUsername());
            theAccount.setPassword(encoder.encode(signUpRequest.getPassword()));
            theAccount.setFullName(signUpRequest.getFullname());
            theAccount.setEmail(signUpRequest.getEmail());
            theAccount.setPhone(signUpRequest.getPhoneNumber());
            theAccount.setCreateDate(LocalDateTime.now());
            theAccount.setStatus(Long.valueOf("1"));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER).orElseThrow(() -> {
                LOGGER.info("Role " + ERole.ROLE_CUSTOMER.name() + " is not found");
                return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
            });
            roles.add(userRole);
            theAccount.setRoles(roles);
            accountRepository.save(theAccount);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Fail to create new account " + signUpRequest.getUsername());
            throw new CreateDataFailException(ErrorCode.ERR_ACCOUNT_SIGNUP_FAIL);
        }
        return result;
    }

    @Override
    public Account getAccountDetail(Long accountId) throws DataNotFoundException {
        Account account;
        try {
            Optional<Account> optinalAccount = accountRepository.findById(accountId);
            if (optinalAccount.isPresent()) {
                account = optinalAccount.get();
            } else {
                LOGGER.info("Can't find account with id: " + accountId);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when load account with id: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    @Override
    public Account getAccountByUsername(String username) throws AccountNotFoundException {
        Optional<Account> result = accountRepository.findByUserName(username);
        Account theAccount;
        if (result.isPresent()) {
            theAccount = result.get();
        } else {
            LOGGER.info("Having error when load account with this username " + username);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return theAccount;
    }

    @Override
    public Boolean updateAccount(Account theAccount) throws UpdateDataFailException {
        boolean result;
        try {
            accountRepository.save(theAccount);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Update account with this username: " + theAccount.getUserName() + " fail");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAccount(Long id) throws DeleteDataFailException {
        boolean result;
        Account account;
        try {
            Optional<Account> accountOptional = accountRepository.findById(id);
            if (accountOptional.isPresent()) {
                account = accountOptional.get();
            } else {
                LOGGER.info("Can't find account with the id: " + id);
                throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
            accountRepository.updateAccountStatusToLocked(account.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't lock account with the id: " + id);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
        return result;
    }

    @Override
    public Boolean activeAccount(Long id) throws UpdateDataFailException {
        boolean result;
        Account account;
        try {
            Optional<Account> accountOptional = accountRepository.findById(id);
            if (accountOptional.isPresent()) {
                account = accountOptional.get();
            } else {
                LOGGER.info("Can't find account with the id: " + id);
                throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
            accountRepository.updateAccountStatusToActive(account.getId());
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't unlock account with the id: " + id);
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
        return result;
    }

    @Override
    public Account getForgotAccount(String username, String fullname, String email, String phone) {
        return accountRepository.getForForgotPassword(username, fullname, email, phone);
    }

    @Override
    public AccountProfileDTO getAccountToShow(Long accountId) throws DataNotFoundException {
        AccountProfileDTO dto;
        try {
            Account account;
            Optional<Account> optionalAccount = accountRepository.findById(accountId);
            if (optionalAccount.isPresent()) {
                account = optionalAccount.get();
            } else {
                LOGGER.info("Can't find account with id: " + accountId);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
            dto = accountConverter.convertAccountProfileToDto(account);
        } catch (Exception ex) {
            LOGGER.info("Can't find account with id: " + accountId);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return dto;
    }

    @Override
    public List<AccountProfileDTO> getListAccountProfilesForAdmin(int pageNo, String valueSort) {
        List<AccountProfileDTO> listDTO;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
            Page<Account> page = accountRepository.findAll(pageable);
            listDTO = accountConverter.toDTOList(page.getContent());
        } catch (Exception e) {
            LOGGER.info("Having error when load list of account: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

}