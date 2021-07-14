package nashtech.phucldh.ecommerce.service;

import java.util.List;
import java.util.UUID;

import nashtech.phucldh.ecommerce.entity.Coupons;

public interface CouponsService {
	
	public List<Coupons> findAll();
	
	public Coupons getCouponById(UUID id);
	
	public List<Coupons> findByItem(Integer productdiscount);
	
	public Coupons getCouponByCode(String code);
	
	public void saveCoupon(Coupons theCoupon);
	
	public void deleteCouponById(String idCoupon);
}
