package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.AccountStatus;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.AccountStatusRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AccountStatusRepositoryTest {

    @Autowired
    AccountStatusRepository accountStatusRepository;

    @Test
    public void addStatus() {
        AccountStatus status = new AccountStatus();
        status.setStatus("Deleted");
        Assert.notNull(accountStatusRepository.save(status));
    }

    @Test
    public void updateStatus() throws DataNotFoundException {
        AccountStatus status = null;
        Optional<AccountStatus> result = accountStatusRepository.findById(Long.valueOf("27"));
        if (result.isPresent()){
            status = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_NOT_FOUND);
        }
        status.setStatus("New Status");
        Assert.notNull(accountStatusRepository.save(status));
    }

    @Test
    public void deleteStatus() throws DataNotFoundException {
        AccountStatus status = null;
        Optional<AccountStatus> result = accountStatusRepository.findById(Long.valueOf("28"));
        if (result.isPresent()){
            status = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_NOT_FOUND);
        }
        accountStatusRepository.deleteById(Long.valueOf("28"));
        Optional<AccountStatus> resultAfterDelete = accountStatusRepository.findById(Long.valueOf("28"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void getStatusById() {
        Optional<AccountStatus> status = accountStatusRepository.findById(Long.valueOf("1"));
        Assert.notNull(status);
    }

    @Test
    public void getAllStatus() {
        List<AccountStatus> list = accountStatusRepository.findAll();
        boolean result = (list.size() == 3);
        Assert.isTrue(result);
    }
}
