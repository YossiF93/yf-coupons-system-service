package CouponSystemProjectJB.CoupoSystemFinalProject.services;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.LoginException;
import org.springframework.validation.annotation.Validated;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.ClientType;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Coupon;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Customer;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.CouponSystemException;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.PurchesedExecption;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.unfoundExecption;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.CouponType;

/**
 * <h3>Customer Service</h3> This Class defying All the Functions Available for a User from type - Customer.
 * 
*/
@Validated
public interface ICustomerService {

 
	public void PurchasedCoupon(@Positive long couponId, @Positive long customerId) throws CouponSystemException,PurchesedExecption,unfoundExecption;
 
	public Customer getCustomer(@Positive long customerId) throws CouponSystemException;
 
	public List<Coupon> getAvailableCoupons(@Positive long customerId) throws CouponSystemException,unfoundExecption;
 
	public List<Coupon> getCustomerCoupons(@Positive long customerId) throws unfoundExecption;
 
	public List<Coupon> getCustomerCouponsByType(@Positive long customerId, @NotNull CouponType couponType) throws unfoundExecption;

	public List<Coupon> getCustomerCouponsByPrice(@Positive long customerId, @Positive @NotNull double price) throws unfoundExecption;

	public Customer login(@NotNull String name, @NotNull String password, @Email String email,@NotNull ClientType clientType) throws LoginException;

}
