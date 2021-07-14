package nashtech.phucldh.ecommerce.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.service.CouponsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/coupons")
public class CouponsRestController {
	
//	private CouponsService couponsService;
//	
//	@Autowired
//	public CouponsRestController(CouponsService couponsService) {
//		this.couponsService = couponsService;
//	}
//	
//	@GetMapping
//	public List<Coupons> getListCategory() {
//		return couponsService.findAll();
//	}
	
//	@GetMapping("/{productdiscount}")
//	public List<Coupons> getCouponsByItemDiscount(@PathVariable Integer id) {
//		List<Coupons> theCouponsList = couponsService.findByItem(id);
//		if (theCouponsList == null) {
//			throw new CouponsNotFoundException("Don't have any coupons for this item: " + id );
//		}
//		return theCouponsList;
//	}
//	
//	@GetMapping("/{couponcode}")
//	public Coupons getCouponsByItemDiscount(@PathVariable String id) {
//		Coupons theCoupons = couponsService.getCouponByCode(id);
//		if (theCoupons == null) {
//			throw new CouponsNotFoundException("Don't have any coupons have this code: " + id );
//		}
//		return theCoupons;
//	}
//	
//	@PostMapping
//	public void addCoupons(@RequestBody Coupons theCoupons) {
//		couponsService.saveCoupon(theCoupons);
//	}
//
//	@PutMapping
//	public void updateCoupons(@RequestBody Coupons updateCoupons) {
//		Coupons tempCategory = couponsService.getCouponById(updateCoupons.getCouponid());
//		if (tempCategory == null) {
//			throw new CouponsNotFoundException("Coupons id: " + updateCoupons.getCouponid() + " not found!");
//		}
//		couponsService.saveCoupon(updateCoupons);
//	}
//
//	@DeleteMapping("/{couponCode}")
//	public String deleteCoupons(@PathVariable String couponCode) {
//		Coupons tempCoupons = couponsService.getCouponByCode(couponCode);
//		if (tempCoupons == null) {
//			throw new CouponsNotFoundException("Coupons code: " + couponCode + " not found!");
//		}
//		couponsService.deleteCouponById(couponCode);
//		return "Delete category code: " + couponCode;
//	}
}
