package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface CouponsService {

	public List<Coupons> findAllCoupons() throws DataNotFoundException;

	public List<Coupons> findByItem(Long itemID) throws DataNotFoundException;

	public Coupons getCouponById(Long id) throws DataNotFoundException;

	public Coupons getCouponByCode(String code) throws DataNotFoundException;

	public void createCoupon(Coupons theCoupon) throws CreateDataFailException;

	public void deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException;

	public void unDeleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException, UpdateDataFailException;

	public Coupons getCouponCanUseByCode(String code) throws DataNotFoundException;

	public List<Coupons> getListCouponByItem(Long itemID) throws DataNotFoundException;
}
