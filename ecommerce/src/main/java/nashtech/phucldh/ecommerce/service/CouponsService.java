package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface CouponsService {

	public List<Coupons> findAll();

	public List<Coupons> findByItem(Integer productdiscount);

	public Coupons getCouponById(Integer id) throws DataNotFoundException;

	public Coupons getCouponByCode(String code) throws DataNotFoundException;

	public void saveCoupon(Coupons theCoupon);

	public void deleteCouponById(String idCoupon);
}
