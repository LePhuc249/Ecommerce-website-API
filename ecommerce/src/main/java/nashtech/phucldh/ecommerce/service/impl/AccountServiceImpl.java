package nashtech.phucldh.ecommerce.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

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
	public ResponseEntity<?> authenticateAccount(LoginRequest loginRequest) {
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
	}

	@Override
	public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) throws CreateDataFailException {
		try {
			boolean existedEmail = accountRepository.existsByUsername(signUpRequest.getUsername());
			if (existedEmail) {
				LOGGER.info("Email %s is already taken", signUpRequest.getEmail());
				throw new DuplicateDataException(ErrorCode.ERR_EMAIL_ALREADY_TAKEN);
			}
			Account theAccount = new Account();
			theAccount.setEmail(signUpRequest.getEmail());
			theAccount.setPassword(encoder.encode(signUpRequest.getPassword()));
			theAccount.setFullname(signUpRequest.getFullname());
			theAccount.setEmail(signUpRequest.getEmail());
			theAccount.setPhone(signUpRequest.getPhoneNumber());
			theAccount.setStatus(signUpRequest.getStatusaccount());
			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.Customer).orElseThrow(() -> {
					LOGGER.info("Role %s is not found", ERole.Customer.name());
					return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
				});
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					if ("admin".equals(role)) {
						Role adminRole = null;
						try {
							adminRole = roleRepository.findByName(ERole.Admin).orElseThrow(() -> {
								LOGGER.info("Role %s is not found", ERole.Admin.name());
								return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
							});
						} catch (DataNotFoundException e) {
							e.printStackTrace();
						}
						roles.add(adminRole);

					} else if ("mod".equals(role)) {
						Role userRole = null;
						try {
							userRole = roleRepository.findByName(ERole.Manager).orElseThrow(() -> {
								LOGGER.info("Role %s is not found", ERole.Manager.name());
								return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
							});
						} catch (DataNotFoundException e) {
							e.printStackTrace();
						}
						roles.add(userRole);
					} else {
						Role userRole = null;
						try {
							userRole = roleRepository.findByName(ERole.Customer).orElseThrow(() -> {
								LOGGER.info("Role %s is not found", ERole.Customer.name());
								return new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
							});
						} catch (DataNotFoundException e) {
							e.printStackTrace();
						}
						roles.add(userRole);
					}
				});
			}
			theAccount.setRoles(roles);
			accountRepository.save(theAccount);
			return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
		} catch (Exception ex) {
			LOGGER.info("Fail to create new account %s", signUpRequest.getEmail());
			throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_FAIL);
		}
	}

	@Override
	public Account getAccountByEmail(String email) throws AccountNotFoundException {
		Optional<Account> result = accountRepository.findByEmail(email);
		Account theAccount = null;
		if (result.isPresent()) {
			theAccount = result.get();
		} else {
			throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
		}
		return theAccount;
	}

	@Override
	public void updateAccount(Account theAccount) throws UpdateDataFailException {
		try {
			accountRepository.save(theAccount);
		} catch (Exception ex) {
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
			throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
		}
		try {
			accountRepository.updateAccountStatusToLocked(account.getId());
		} catch (Exception ex) {
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
			throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
		}
		try {
			accountRepository.updateAccountStatusToActive(account.getId());
		} catch (Exception ex) {
			throw new UpdateDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
		}
	}

}
