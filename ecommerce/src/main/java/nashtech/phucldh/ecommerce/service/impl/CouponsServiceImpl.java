package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CouponsRepository;
import nashtech.phucldh.ecommerce.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponsServiceImpl.class);

    @Autowired
    CouponsRepository couponsRepository;

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
            couponsRepository.findByProductdiscount(itemID);
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
        } else {
            LOGGER.info("Can't find coupons by code " + code);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return theCoupon;
    }

    @Override
    public Coupons getCouponById(Long id) throws DataNotFoundException {
        Optional<Coupons> result = couponsRepository.findById(id);
        Coupons theCoupon = null;
        if (result.isPresent()) {
            theCoupon = result.get();
        } else {
            LOGGER.info("Can't find coupon by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return theCoupon;
    }

    @Override
    public void createCoupon(Coupons theCoupon) throws CreateDataFailException {
        try {
            couponsRepository.save(theCoupon);
        } catch (Exception ex) {
            LOGGER.info("Can't create new coupon ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
        }
    }

    @Override
    public void updateCoupon(Coupons theCoupon) throws UpdateDataFailException {
        try {
            Optional<Coupons> tempOptional = couponsRepository.findById(theCoupon.getId());
            if (!tempOptional.isPresent()) {
                LOGGER.info("Can't find coupon by id " + theCoupon.getId());
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsRepository.save(theCoupon);
        } catch (Exception ex) {
            LOGGER.info("Can't create new coupon ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
    }

    @Override
    public void deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException {
        Coupons coupon = null;
        Optional<Coupons> couponOptional = couponsRepository.findById(id);
        if (couponOptional.isPresent()) {
            coupon = couponOptional.get();
        } else {
            LOGGER.info("Can't find coupon by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        try {
            couponsRepository.deleteCoupons(coupon.getId());
        } catch (Exception ex) {
            LOGGER.info("Can't delete coupon by id " + id);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
        }
    }

    @Override
    public void unDeleteCoupon(Long id) throws DataNotFoundException, UpdateDataFailException {
        Coupons coupon = null;
        Optional<Coupons> couponOptional = couponsRepository.findById(id);
        if (couponOptional.isPresent()) {
            coupon = couponOptional.get();
        } else {
            LOGGER.info("Can't find coupon by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        try {
            couponsRepository.unDeleteCoupons(coupon.getId());
        } catch (Exception ex) {
            LOGGER.info("Can't update coupon by id " + id);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
    }

    @Override
    public Coupons getCouponCanUseByCode(String code) throws DataNotFoundException {
        Coupons coupon = null;
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
        List<Coupons> listByItem = null;
        try {
            listByItem = couponsRepository.findCouponsByItem(itemID);
        } catch (Exception e) {
            LOGGER.info("Can't update coupon by id " + itemID);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return listByItem;
    }

}