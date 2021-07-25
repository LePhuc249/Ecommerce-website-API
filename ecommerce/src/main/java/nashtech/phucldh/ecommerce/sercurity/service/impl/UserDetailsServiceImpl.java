package nashtech.phucldh.ecommerce.sercurity.service.impl;

import javax.transaction.Transactional;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Account;

import nashtech.phucldh.ecommerce.repository.AccountReponsitory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountReponsitory accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND));
        return UserDetailsImpl.build(account);
    }

}