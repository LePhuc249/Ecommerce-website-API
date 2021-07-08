package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.CouponsNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CouponsReponsitory;
import nashtech.phucldh.ecommerce.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService{
	
	@Autowired
	CouponsReponsitory couponsRepository;

	@Override
	public List<Coupons> findAll() {
		List<Coupons> theListCoupons = couponsRepository.findAll();
		return theListCoupons;
	}

	@Override
	public List<Coupons> findByItem(String productdiscount) {
		List<Coupons> theListCoupons = couponsRepository.findByProductdiscount(productdiscount);
		return theListCoupons;
	}

	@Override
	public Coupons getCouponByCode(String code) {
		Optional<Coupons> result = couponsRepository.findById(code);
		Coupons theCoupon = null;
		if (result.isPresent()) {
			theCoupon = result.get();
		} else {
			throw new CouponsNotFoundException("Did not find coupon by code - " + code);
		}
		return theCoupon;
	}

	@Override
	public void saveCoupon(Coupons theCoupon) {
		couponsRepository.save(theCoupon);
	}

	@Override
	public void deleteCouponById(String idCoupon) {
		couponsRepository.deleteById(idCoupon);
	}

}
