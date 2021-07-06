package nashtech.phucldh.ecommerce.sercurity.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.reponsitory.AccountReponsitory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountReponsitory accountRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User can't found with username: " + username));
		return UserDetailsImpl.build(account);
	}
}
