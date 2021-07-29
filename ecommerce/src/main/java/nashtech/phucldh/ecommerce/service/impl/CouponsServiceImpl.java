package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.CouponsConverter;
import nashtech.phucldh.ecommerce.dto.Coupons.CouponsDTO;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.CouponsRepository;
import nashtech.phucldh.ecommerce.service.CouponsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CouponsServiceImpl implements CouponsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponsServiceImpl.class);

    @Autowired
    CouponsRepository couponsRepository;

    @Autowired
    CouponsConverter couponsConverter;

    @Override
    public Coupons getCouponByCode(String code) throws DataNotFoundException {
        Optional<Coupons> result = couponsRepository.findByCode(code);
        Coupons theCoupon = null;
        if (result.isPresent()) {
            theCoupon = result.get();
        }
        return theCoupon;
    }

    @Override
    public Coupons getCouponById(Long id) throws DataNotFoundException {
        Optional<Coupons> result = couponsRepository.findById(id);
        Coupons theCoupon;
        if (result.isPresent()) {
            theCoupon = result.get();
        } else {
            LOGGER.info("Can't find coupon by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return theCoupon;
    }

    @Override
    public Boolean createCoupon(CouponsDTO dtoCoupon) throws CreateDataFailException {
        boolean result;
        try {
            Coupons coupons = couponsConverter.convertCouponsDTOToEntity(dtoCoupon);
            Coupons tempCoupons = getCouponByCode(dtoCoupon.getCode());
            if (tempCoupons != null) {
                LOGGER.info("Coupon have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_COUPONS_EXISTED);
            }
            coupons.setCreateDate(LocalDateTime.now());
            coupons.setDeleted(false);
            couponsRepository.save(coupons);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create new coupon " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateCoupon(CouponsDTO dtoCoupon) throws UpdateDataFailException {
        boolean result;
        try {
            Coupons coupons = couponsConverter.convertCouponsDTOToEntity(dtoCoupon);
            Coupons tempCoupons = getCouponByCode(dtoCoupon.getCode());
            if (tempCoupons == null) {
                LOGGER.info("Can't find coupon by id " + coupons.getId());
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            coupons.setCreateDate(tempCoupons.getCreateDate());
            coupons.setUpdateDate(LocalDateTime.now());
            couponsRepository.save(coupons);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create new coupon " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Coupons coupon;
        try {
            Optional<Coupons> couponOptional = couponsRepository.findById(id);
            if (couponOptional.isPresent()) {
                coupon = couponOptional.get();
            } else {
                LOGGER.info("Can't find coupon by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsRepository.deleteCoupons(coupon.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete coupon " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean unDeleteCoupon(Long id) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Coupons coupon;
        try {
            Optional<Coupons> couponOptional = couponsRepository.findById(id);
            if (couponOptional.isPresent()) {
                coupon = couponOptional.get();
            } else {
                LOGGER.info("Can't find coupon by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsRepository.unDeleteCoupons(coupon.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update coupon " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return result;
    }

    @Override
    public CouponsDTO getCouponsToShow(Long id) throws DataNotFoundException {
        CouponsDTO dto;
        try {
            Optional<Coupons> result = couponsRepository.findById(id);
            Coupons theCoupon;
            if (result.isPresent()) {
                theCoupon = result.get();
            } else {
                LOGGER.info("Can't find coupon by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            dto = couponsConverter.convertCouponsToDTO(theCoupon);
        } catch (Exception e) {
            LOGGER.info("Having error when load the coupons " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_LOADED_FAIL);
        }
        return dto;
    }

    @Override
    public List<CouponsDTO> getListCouponsToShow(int pageNo, String valueSort) {
        List<CouponsDTO> listDTO;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
            Page<Coupons> page = couponsRepository.findAll(pageable);
            listDTO = couponsConverter.toDTOList(page.getContent());
        } catch (Exception e) {
            LOGGER.info("Having error when load list coupons " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public Coupons getCouponCanUseByCode(String code) throws DataNotFoundException {
        Coupons coupon;
        Optional<Coupons> couponOptional = couponsRepository.canUse(code);
        if (couponOptional.isPresent()) {
            coupon = couponOptional.get();
        } else {
            LOGGER.info("Can't find coupon by code " + code);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return coupon;
    }

    @Override
    public List<Coupons> getListCouponByItem(Long itemID) throws DataNotFoundException {
        List<Coupons> listByItem;
        try {
            listByItem = couponsRepository.findCouponsByItem(itemID);
        } catch (Exception e) {
            LOGGER.info("Having error when update coupon " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return listByItem;
    }

}