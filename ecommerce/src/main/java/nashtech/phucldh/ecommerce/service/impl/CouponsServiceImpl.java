package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.converter.CouponsConverter;

import nashtech.phucldh.ecommerce.dto.CouponsDTO;

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
    public List<Coupons> findAllCoupons() throws DataNotFoundException {
        List<Coupons> theListCoupons = null;
        try {
            theListCoupons = couponsRepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find all coupons ");
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return theListCoupons;
    }

    @Override
    public List<Coupons> findByItem(Long itemID) throws DataNotFoundException {
        List<Coupons> theListCoupons = null;
        try {
            couponsRepository.findByProductDiscount(itemID);
        } catch (Exception ex) {
            LOGGER.info("Can't find coupon by item " + itemID);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return theListCoupons;
    }

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
        } catch (Exception ex) {
            LOGGER.info("Can't create new coupon ");
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
            coupons.setUpdateDate(LocalDateTime.now());
            couponsRepository.save(coupons);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't create new coupon ");
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
        } catch (Exception ex) {
            LOGGER.info("Can't delete coupon by id " + id);
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
        } catch (Exception ex) {
            LOGGER.info("Can't update coupon by id " + id);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return result;
    }

    @Override
    public Page<Coupons> getPaginationCoupons(int pageNo, String valueSort) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
        Page<Coupons> page = couponsRepository.findAll(pageable);
        return page;
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
            LOGGER.info("Can't update coupon by id " + itemID);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return listByItem;
    }

}