package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.AccountAddress;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.AccountNotFoundException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AccountAddressRepositoryTest {

    @Autowired
    AccountReponsitory accountRepository;

    @Autowired
    AccountAddressRepository accountaddressRepository;

    @Test
    public void addAddress() throws AccountNotFoundException {
        AccountAddress address = new AccountAddress();
        Account theAccount;
        Optional<Account> result = accountRepository.findById(Long.valueOf("1"));
        if (result.isPresent()) {
            theAccount = result.get();
        } else {
            throw new AccountNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        address.setAccount(theAccount);
        address.setAddress("Test Address");
        Assert.notNull(accountaddressRepository.save(address));
    }

    @Test
    public void updateAddress() throws DataNotFoundException {
        AccountAddress address;
        Optional<AccountAddress> result = accountaddressRepository.findById(Long.valueOf("21"));
        if (result.isPresent()) {
            address = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        address.setAddress("New Address");
        Assert.notNull(accountaddressRepository.save(address));
    }

    @Test
    public void deleteAddress() throws DataNotFoundException {
        AccountAddress address;
        Optional<AccountAddress> result = accountaddressRepository.findById(Long.valueOf("24"));
        if (result.isPresent()) {
            address = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        accountaddressRepository.deleteById(address.getId());
        Optional<AccountAddress> resultAfterDelete = accountaddressRepository.findById(Long.valueOf("24"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void getListAddress() throws DataNotFoundException{
        List<AccountAddress> list = accountaddressRepository.findAddressByAccountId(Long.valueOf("1"));
        if (list.size() == 0) {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        boolean result = (list.size() == 1);
        Assert.isTrue(result);
    }

    @Test
    public void getList() throws DataNotFoundException{
        List<AccountAddress> list = accountaddressRepository.findAll();
        boolean result = (list.size() == 21);
        Assert.isTrue(result);
    }

    @Test
    public void checkGetAddress() throws DataNotFoundException{
        Optional<AccountAddress> result = accountaddressRepository.findById(Long.valueOf("20"));
        Assert.notNull(result);
    }

}
