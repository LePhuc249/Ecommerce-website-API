package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Account;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.AccountNotFoundException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountReponsitory accountRepository;

    @Test
    public void addAccount() {
        Account account = new Account();
        account.setUserName("Test Add Username");
        account.setPassword("Test Add Password");
        account.setFullName("Test Add Full name");
        account.setEmail("Test Add Email");
        account.setPhone("Test Phone");
        account.setStatus(Long.valueOf("2"));
        Assert.notNull(accountRepository.save(account));
    }

    @Test
    public void updateAccount() throws AccountNotFoundException {
        Account account;
        Optional<Account> result = accountRepository.findById(Long.valueOf("26"));
        if (result.isPresent()){
            account = result.get();
        } else {
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        account.setStatus(Long.valueOf("3"));
        Assert.notNull(accountRepository.save(account));
    }

    @Test
    public void deleteAccount() throws AccountNotFoundException {
        Account account = null;
        Optional<Account> result = accountRepository.findById(Long.valueOf("26"));
        if (result.isPresent()){
            account = result.get();
        } else {
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        accountRepository.deleteById(Long.valueOf("26"));
        Optional<Account> resultAfterDelete = accountRepository.findById(Long.valueOf("26"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void checkFunctionFindByUsername() {
        Optional<Account> result = accountRepository.findByUserName("admin");
        Assert.notNull(result);
    }

    @Test
    public void checkFunctionFindByEmail() {
        Optional<Account> result = accountRepository.findByEmail("manager1@gmail.com");
        Assert.notNull(result);
    }

    @Test
    public void checkFunctionFindByUsernameAndPassword() {
        Optional<Account> result = accountRepository.findByUserNameAndPassword("admin", "admin");
        Assert.notNull(result);
    }

    @Test
    public void checkFunctionExistsByUsername() {
        boolean result = accountRepository.existsByUserName("admin");
        Assert.isTrue(result);
    }

    @Test
    public void checkFunctionExistsByEmail() {
        boolean result = accountRepository.existsByEmail("admin@gmail.com");
        Assert.isTrue(result);
    }

    @Test
    public void getListAccount() {
        List<Account> list = accountRepository.findAll();
        boolean result = (list.size() == 20);
        Assert.isTrue(result);
    }

    @Test
    public void checkFunctionUpdateAccountStatusToActive() {
        int num = accountRepository.updateAccountStatusToActive(Long.valueOf("2"));
        boolean result = (num > 0);
        Assert.isTrue(result);
    }

    @Test
    public void checkFunctionUpdateAccountStatusToLocked() {
        int num = accountRepository.updateAccountStatusToLocked(Long.valueOf("2"));
        boolean result = (num > 0);
        Assert.isTrue(result);
    }

}
