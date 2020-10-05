package backend.service;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import backend.exceptions.LoginException;
import org.springframework.validation.annotation.Validated;
import backend.models.ClientType;
import backend.entities.Coupon;
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;
import backend.exceptions.PurchesedExecption;
import backend.exceptions.unfoundExecption;
import backend.models.CouponType;

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
