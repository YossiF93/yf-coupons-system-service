package CouponSystemProjectJB.CoupoSystemFinalProject.services;
 
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.LoginException;
import org.springframework.validation.annotation.Validated;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.Admin;
import CouponSystemProjectJB.CoupoSystemFinalProject.models.ClientType;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Company;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Coupon;
import CouponSystemProjectJB.CoupoSystemFinalProject.entities.Customer;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.CouponSystemException;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.unfoundExecption;
 
/**
 * <h3>Admin Service</h3> This Class defying All the Functions Available for a User from type - Admin.
 * 
*/
@Validated
public interface IAdminService {
	
	public void createCompany ( @Valid Company company) throws CouponSystemException,unfoundExecption;
	
	public void removeCompany(@Positive long companyId) throws CouponSystemException,unfoundExecption;
	
	public void updateCompany( @Valid Company company)  throws CouponSystemException,unfoundExecption;
	
	public void createCustomer(@Valid Customer customer) throws CouponSystemException,unfoundExecption;
	
	public void removeCustomer(@Positive long customerId)throws CouponSystemException,unfoundExecption;
	
	public void updateCustomer(@Valid Customer customer) throws CouponSystemException,unfoundExecption;
	
	public Company getCompany(@Positive long companyId) throws unfoundExecption;
	
	public  List<Company> getAllCompanies() throws unfoundExecption;
	
	public Customer getCustomer(@Positive long customerId) throws unfoundExecption;
	
	public List<Customer> getAllCustomers() throws unfoundExecption;

	public Coupon getCoupon(@Positive long couponId) throws CouponSystemException;

	public List<Coupon> GetAllCoupons() throws CouponSystemException;
	
	public  Admin login(String name, String password,String email, ClientType clientType) throws LoginException;
}
