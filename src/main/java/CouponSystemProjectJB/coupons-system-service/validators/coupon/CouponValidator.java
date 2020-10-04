package CouponSystemProjectJB.CoupoSystemFinalProject.validators.coupon;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Coupon;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Customer;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.PurchesedExecption;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.unfoundExecption;
import CouponSystemProjectJB.CoupoSystemFinalProject.repositories.CouponJpaRepository;
import CouponSystemProjectJB.CoupoSystemFinalProject.repositories.CustomerJpaRepository;
import CouponSystemProjectJB.CoupoSystemFinalProject.util.Util;

@RequestScope
@Component
public class CouponValidator {

	@Autowired
	CouponJpaRepository couponJpaRepository;
	
	@Autowired
	CustomerJpaRepository customerJpaRepository;
	
	public Optional<Customer> toPurchasedCouponValidation(Long couponId , Long customerId) throws PurchesedExecption,unfoundExecption{
		
		Optional<Coupon> coupon = couponJpaRepository.findById(couponId);
		if (coupon.isEmpty()) {
			throw new unfoundExecption("Error - Coupon Not Found in the system , Cannot Purcesd Coupon");

		}

		Optional<Customer> customer = customerJpaRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new unfoundExecption("Error - Customer Not Found in the system , Cannot Purcesd Coupon");
		}

		if (coupon.get().getAmount() <= 0) {
			throw new PurchesedExecption("Error - Coupon is out of stock , Cannot Purcesd Coupon");

		}

		if (Util.GetCurrentDate().compareTo(coupon.get().getEndDate()) > 0) {
			throw new PurchesedExecption("Error - Coupon is out of date , Cannot Purcesd Coupon");

		}

		List<Coupon> couponList = customerJpaRepository.getCustomerCoupons(customer.get().getId());
		for (Coupon couponT : couponList) {
			if (couponT.getId() == coupon.get().getId()) {
				throw new PurchesedExecption("Error - Coupon Already Been Bought By You, Cannot Purcesd Coupon");
			}
		}
		return customer;
	}
 
	
}

