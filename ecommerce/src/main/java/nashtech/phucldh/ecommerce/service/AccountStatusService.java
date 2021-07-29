package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.AccountStatus.AccountStatusDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AccountStatusService {

    List<AccountStatusDTO> getAllStatus();

    Boolean createNewAccountStatus(AccountStatusDTO dto) throws CreateDataFailException;

    Boolean updateAccountStatus(AccountStatusDTO dto) throws UpdateDataFailException;

    Boolean deleteAccountStatus(Long id) throws DeleteDataFailException;

}
