package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
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

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountReponsitory accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AccountResponse accountResponse;

	@Autowired
	JwtUtils jwtUtils;

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
	    theAccount.setRoleid(signUpRequest.getRoleid());
	    accountRepository.save(theAccount);
	    return accountResponse.generateMessageResponseEntity("Account have been registered successfully!", HttpStatus.CREATED);
	}

}
