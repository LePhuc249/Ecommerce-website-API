package nashtech.phucldh.ecommerce.service;

import org.springframework.http.ResponseEntity;

//import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.payload.request.LoginRequest;
import nashtech.phucldh.ecommerce.payload.request.SignUpRequest;

public interface AccountService {
	
	public ResponseEntity<?> authenticateAccount(LoginRequest loginRequest);

    public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest);

//	public Account getAccountByEmail(String email);
//
//	public String getRoleByEmail(String email);
//
//	public void saveAccount(Account account);
//
//	public void deleteAccount(String username);
}
