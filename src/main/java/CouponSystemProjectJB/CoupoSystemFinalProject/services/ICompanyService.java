package CouponSystemProjectJB.CoupoSystemFinalProject.services;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.CouponType;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.CouponSystemException;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.InvalidParamException;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.LoginException;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.unfoundExecption;

import java.sql.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.ClientType;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Company;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Coupon;

 
/**
 * <h3>Company Service</h3> This Class defying All the Functions Available for a User from type - Company.
 * 
*/
@Validated
public interface ICompanyService {

	public void createCoupon(@Positive long companyId,@Valid Coupon coupon) throws CouponSystemException,InvalidParamException,unfoundExecption;

	public void removeCoupon(@Positive long companyId, @Positive long couponId) throws CouponSystemException,unfoundExecption;

	public void updateCoupon(@Positive long companyId,@Positive long couponId,@NotNull Date endDate, @NotNull @Positive Double price) throws  CouponSystemException,unfoundExecption;

	public Company getCompany(@Positive long companyId) throws unfoundExecption ;

	public List<Coupon> getCompanyCoupons(@Positive long companyId) throws unfoundExecption;
	
	public List<Coupon> getCompanyCouponsByType(@Positive long companyId, @NotNull CouponType couponType) throws unfoundExecption ;
	
	public List<Coupon> getCompanyCouponsByPrice(@Positive long companyId, @Positive @NotNull double price) throws unfoundExecption ;

	public List<Coupon> getCompanyCouponsByDate(@Positive long companyId, @NotNull Date date) throws unfoundExecption;
	
	public Company login(@NotNull String name,@NotNull String password,@Email String email,@NotNull ClientType clientType) throws LoginException;
}
