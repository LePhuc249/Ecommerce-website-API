package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CouponsRepository;
import nashtech.phucldh.ecommerce.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {

	@Autowired
    CouponsRepository couponsRepository;

	@Override
	public List<Coupons> findAllCoupons() throws DataNotFoundException {
		List<Coupons> theListCoupons = null;
		try {
			theListCoupons = couponsRepository.findAll();
		} catch (Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND); // add error code about can't find list
		}
		return theListCoupons;
	}

	@Override
	public List<Coupons> findByItem(Long itemID) throws DataNotFoundException {
		List<Coupons> theListCoupons = null;
		try {
			couponsRepository.findByProductdiscount(itemID);
		} catch (Exception ex) {
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
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
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
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theCoupon;
	}

	@Override
	public void createCoupon(Coupons theCoupon) throws CreateDataFailException {
		try {
			couponsRepository.save(theCoupon);
		} catch (Exception ex) {
			throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
		}
	}

	@Override
	public void deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException {
		Coupons coupon = null;
		Optional<Coupons> couponOptional = couponsRepository.findById(id);
		if (couponOptional.isPresent()) {
			coupon = couponOptional.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
		}
		try {
			couponsRepository.deleteCoupons(coupon.getId());
		} catch(Exception ex ) {
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
			throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
		}
		try {
			couponsRepository.unDeleteCoupons(coupon.getId());
		} catch(Exception ex ) {
			throw new UpdateDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
		}
	}

	@Override
	public Coupons getCouponCanUseByCode(String code) throws DataNotFoundException {
		Coupons coupon = null;
		Optional<Coupons> couponOptional = couponsRepository.canUse(code);
		if (couponOptional.isPresent()) {
			coupon = couponOptional.get();
		} else {
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
			throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
		}
		return listByItem;
	}

}
