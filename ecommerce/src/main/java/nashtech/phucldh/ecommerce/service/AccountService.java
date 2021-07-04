package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Account;

public interface AccountService {

	public Account checkLogin(String username, String password);

	public Account getAccountByEmail(String email);

	public String getRoleByEmail(String email);

	public void saveAccount(Account account);

	public void deleteAccount(String username);
}
