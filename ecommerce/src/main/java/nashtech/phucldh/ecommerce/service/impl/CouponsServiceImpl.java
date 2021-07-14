package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CouponsReponsitory;
import nashtech.phucldh.ecommerce.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {

	@Autowired
	CouponsReponsitory couponsRepository;

	@Override
	public List<Coupons> findAll() {
		List<Coupons> theListCoupons = couponsRepository.findAll();
		return theListCoupons;
	}

	@Override
	public List<Coupons> findByItem(Integer productdiscount) {
		List<Coupons> theListCoupons = couponsRepository.findByProductdiscount(productdiscount);
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
	public Coupons getCouponById(Integer id) throws DataNotFoundException {
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
	public void saveCoupon(Coupons theCoupon) {
		couponsRepository.save(theCoupon);
	}

	@Override
	public void deleteCouponById(String couponCode) {
		couponsRepository.deleteCouponByCode(couponCode);
	}

}
