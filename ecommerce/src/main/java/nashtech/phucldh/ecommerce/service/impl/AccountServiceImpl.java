package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.AccountResponse;
import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.exception.AccountNotFoundException;
import nashtech.phucldh.ecommerce.exception.RoleNotFoundException;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;
import nashtech.phucldh.ecommerce.payload.response.JwtResponse;
import nashtech.phucldh.ecommerce.reponsitory.AccountReponsitory;
import nashtech.phucldh.ecommerce.reponsitory.RoleRepository;
import nashtech.phucldh.ecommerce.sercurity.jwt.JwtUtils;
import nashtech.phucldh.ecommerce.sercurity.service.impl.UserDetailsImpl;
import nashtech.phucldh.ecommerce.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final String ROLE_NOT_FOUND_MSG = "Can't not found the role.";

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountReponsitory accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AccountResponse accountResponse;

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
	public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) {
		boolean existedEmail = accountRepository.existsByUsername(signUpRequest.getUsername());
		if (existedEmail) {
			return accountResponse.generateMessageResponseEntity("Username have already used.", HttpStatus.CONFLICT);
		}
		Account theAccount = new Account();
		theAccount.setEmail(signUpRequest.getEmail());
		theAccount.setPassword(encoder.encode(signUpRequest.getPassword()));
		theAccount.setFullname(signUpRequest.getFullname());
		theAccount.setEmail(signUpRequest.getEmail());
		theAccount.setPhone(signUpRequest.getPhoneNumber());
		theAccount.setAddress(signUpRequest.getAddress());
		theAccount.setStatusaccount(signUpRequest.getStatusaccount());
		Set<String> strRoles = signUpRequest.getRole();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName("Customer").orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND_MSG));
			roles = userRole;
		} else {
			strRoles.forEach(role -> {
				if ("admin".equals(role)) {
					Role adminRole = roleRepository.findByName("Admin").orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND_MSG));
					roles = adminRole;
				} else {
					Role userRole = roleRepository.findByName("Customer").orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND_MSG));
					roles = userRole;
				}
			});
		}	
		theAccount.setRole(roles);
		accountRepository.save(theAccount);
		return accountResponse.generateMessageResponseEntity("Account have been registered successfully!",
				HttpStatus.CREATED);
	}

	@Override
	public Account getAccountByEmail(String email) {
		Optional<Account> result = accountRepository.findByEmail(email);
		Account theAccount = null;
		if (result.isPresent()) {
			theAccount = result.get();
		} else {
			throw new AccountNotFoundException("Did not find account by email - " + email);
		}
		return theAccount;
	}

	@Override
	public void updateStatus(Account theAccount) {
		accountRepository.save(theAccount);
	}

	@Override
	public void deleteAccount(String username) {
		accountRepository.deleteById(username);
	}

}
