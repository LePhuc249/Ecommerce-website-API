package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Coupons;

public interface CouponsService {
	
	public List<Coupons> findAll();
	
	public List<Coupons> findByItem(String productdiscount);
	
	public Coupons getCouponByCode(String code);
	
	public void saveCoupon(Coupons theCoupon);
	
	public void deleteCouponById(String idCoupon);
}
