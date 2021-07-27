package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;

import nashtech.phucldh.ecommerce.converter.AccountConverter;

import nashtech.phucldh.ecommerce.dto.AccountProfileDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;

import nashtech.phucldh.ecommerce.entity.Account;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountConverter accountConverter;

    @GetMapping("/account/{page}")
    public ResponseEntity<ResponseDTO> getAllAccount(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Account> page = accountService.getPaginationAccount(pageNo, "fullName");
            List<AccountProfileDTO> listDTO = accountConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/account/details/{accountID}")
    public ResponseEntity<ResponseDTO> getAccountDetail(@PathVariable int accountID) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long value = Long.valueOf(String.valueOf(accountID));
            Account account = accountService.getAccountDetail(value);
            if (account != null) {
                AccountProfileDTO dto = accountConverter.convertAccountProfileToDto(account);
                response.setData(dto);
                response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/account/lockAccount/{accountID}")
    public ResponseEntity<ResponseDTO> lockAccount(@PathVariable("accountID") int accountID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = accountService.deleteAccount(Long.valueOf(String.valueOf(accountID)));
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/account/unlockAccount/{accountID}")
    public ResponseEntity<ResponseDTO> activeAccount(@PathVariable("accountID") int accountID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = accountService.activeAccount(Long.valueOf(String.valueOf(accountID)));
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ACCOUNT_ACTIVE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

}