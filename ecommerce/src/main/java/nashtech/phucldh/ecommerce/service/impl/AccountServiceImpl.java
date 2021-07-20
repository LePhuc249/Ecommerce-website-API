package nashtech.phucldh.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import nashtech.phucldh.ecommerce.exception.AccountAuthenticationException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;
import nashtech.phucldh.ecommerce.payload.response.JwtResponse;
import nashtech.phucldh.ecommerce.payload.response.MessageResponse;
import nashtech.phucldh.ecommerce.reponsitory.AccountReponsitory;
import nashtech.phucldh.ecommerce.reponsitory.RoleRepository;
import nashtech.phucldh.ecommerce.sercurity.jwt.JwtUtils;
import nashtech.phucldh.ecommerce.sercurity.service.impl.UserDetailsImpl;
import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.constants.ErrorCode;

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

    Role roles = null;

    @Override
    public ResponseEntity<?> authenticateAccount(LoginRequest loginRequest) throws AccountAuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().header(jwtUtils.getAuthorizationHeader(), jwtUtils.getTokenPrefix() + jwt)
                    .body(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getFullname(), userDetails.getEmail(),
                            roles.get(0)));
        } catch (Exception e) {
            LOGGER.info("Account Authentication Error");
            throw new AccountAuthenticationException(ErrorCode.ERR_ACCOUNT_LOGIN_FAIL);
        }
    }

    @Override
    public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException {
        try {
            boolean existedEmail = accountRepository.existsByUsername(signUpRequest.getUsername());
            if (existedEmail) {
                LOGGER.info("Username " + signUpRequest.getUsername() + " is already taken");
                throw new DuplicateDataException(ErrorCode.ERR_USERNAME_ALREADY_TAKEN);
            }
            Account theAccount = new Account();
            theAccount.setUsername(signUpRequest.getUsername());
            theAccount.setPassword(encoder.encode(signUpRequest.getPassword()));
            theAccount.setFullname(signUpRequest.getFullname());
            theAccount.setEmail(signUpRequest.getEmail());
            theAccount.setPhone(signUpRequest.getPhoneNumber());
            theAccount.setCreatedate(LocalDateTime.now());
            theAccount.setStatus(signUpRequest.getStatus());
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.Customer).orElseThrow(() -> {
                LOGGER.info("Role is not found", ERole.Customer.name());
                return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
            });
            roles.add(userRole);
            theAccount.setRoles(roles);
            accountRepository.save(theAccount);
            return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
        } catch (Exception ex) {
            LOGGER.info("Fail to create new account " + signUpRequest.getUsername());
            throw new CreateDataFailException(ErrorCode.ERR_ACCOUNT_SIGNUP_FAIL);
        }
    }

    @Override
    public Account getAccountByEmail(String email) throws AccountNotFoundException {
        Optional<Account> result = accountRepository.findByEmail(email);
        Account theAccount = null;
        if (result.isPresent()) {
            theAccount = result.get();
        } else {
            LOGGER.info("Can't find account with this email " + email);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return theAccount;
    }

    @Override
    public Account getAccountByUsername(String username) throws AccountNotFoundException {
        Optional<Account> result = accountRepository.findByUsername(username);
        Account theAccount = null;
        if (result.isPresent()) {
            theAccount = result.get();
        } else {
            LOGGER.info("Can't find account with this username " + username);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return theAccount;
    }

    @Override
    public void updateAccount(Account theAccount) throws UpdateDataFailException {
        try {
            accountRepository.save(theAccount);
        } catch (Exception ex) {
            LOGGER.info("Update account with this username %s fail", theAccount.getUsername());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
        }
    }

    @Override
    public void deleteAccount(Long id) throws AccountNotFoundException, DeleteDataFailException {
        Account account = null;
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        } else {
            LOGGER.info("Can't find account with this id %s fail", id);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        try {
            accountRepository.updateAccountStatusToLocked(account.getId());
        } catch (Exception ex) {
            LOGGER.info("Delete account with this username %s fail", account.getUsername());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
    }

    @Override
    public void activeAccount(Long id) throws AccountNotFoundException, UpdateDataFailException {
        Account account = null;
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        } else {
            LOGGER.info("Can't find account with this id %s fail", id);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        try {
            accountRepository.updateAccountStatusToActive(account.getId());
        } catch (Exception ex) {
            LOGGER.info("Update status account with this username %s fail", account.getUsername());
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
    }

    @Override
    public Long getStatusAccount(Long id) throws AccountNotFoundException {
        Account account = null;
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        } else {
            LOGGER.info("Can't find account with this id %s fail", id);
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        Long status = account.getStatus();
        return status;
    }

}