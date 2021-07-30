package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.AccountStatusConverter;
import nashtech.phucldh.ecommerce.dto.AccountStatus.AccountStatusDTO;
import nashtech.phucldh.ecommerce.entity.AccountStatus;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.AccountStatusRepository;
import nashtech.phucldh.ecommerce.service.AccountStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    private AccountStatusConverter accountStatusConverter;

    @Override
    public List<AccountStatusDTO> getAllStatus() {
        List<AccountStatusDTO> list;
        try {
            List<AccountStatus> listStatus = accountStatusRepository.findAll();
            if (listStatus.size() > 0) {
                list = new ArrayList<>();
                for (AccountStatus accountStatus : listStatus) {
                    AccountStatusDTO dto = accountStatusConverter.convertAccountStatusToDTO(accountStatus);
                    list.add(dto);
                }
            } else {
                LOGGER.info("List status of account empty");
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_LIST_EMPTY);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when loading account status: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_LIST_LOADED_FAIL);
        }
        return list;
    }

    @Override
    public Boolean createNewAccountStatus(AccountStatusDTO dto) throws CreateDataFailException {
        boolean result;
        try {
            AccountStatus tempStatus = accountStatusRepository.getStatusByName(dto.getStatus());
            if (tempStatus != null) {
                LOGGER.info("Status with name: " + dto.getStatus() + " have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_ACCOUNT_STATUS_EXISTED);
            }
            AccountStatus status = accountStatusConverter.convertAccountStatusDTOToEntity(dto);
            accountStatusRepository.save(status);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when add new status for account: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateAccountStatus(AccountStatusDTO dto) throws UpdateDataFailException {
        boolean result;
        try {
            Optional<AccountStatus> optionalStatus = accountStatusRepository.findById(dto.getId());
            if (!optionalStatus.isPresent()) {
                LOGGER.info("Can't find status with id: " + dto.getId());
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_NOT_FOUND);
            }
            AccountStatus status = accountStatusConverter.convertAccountStatusDTOToEntity(dto);
            accountStatusRepository.save(status);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update status for account: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_STATUS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAccountStatus(Long id) throws DeleteDataFailException {
        boolean result;
        try {
            Optional<AccountStatus> optionalStatus = accountStatusRepository.findById(id);
            if (!optionalStatus.isPresent()) {
                LOGGER.info("Can't find status with id: " + id);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_STATUS_NOT_FOUND);
            }
            accountStatusRepository.deleteById(id);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update status for account: " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_STATUS_FAIL);
        }
        return result;
    }

}
