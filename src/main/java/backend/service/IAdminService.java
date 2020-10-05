package backend.service;

import backend.entities.Company;
import backend.entities.Coupon;
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;
import backend.exceptions.LoginException;
import backend.exceptions.unfoundExecption;
import backend.models.Admin;
import backend.models.ClientType;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <h3>Admin Service</h3> This Class defying All the Functions Available for a User from type - Admin.
 */
@Validated
public interface IAdminService {

    public void createCompany(@Valid Company company) throws CouponSystemException, unfoundExecption;

    public void removeCompany(@Positive long companyId) throws CouponSystemException, unfoundExecption;

    public void updateCompany(@Valid Company company) throws CouponSystemException, unfoundExecption;

    public void createCustomer(@Valid Customer customer) throws CouponSystemException, unfoundExecption;

    public void removeCustomer(@Positive long customerId) throws CouponSystemException, unfoundExecption;

    public void updateCustomer(@Valid Customer customer) throws CouponSystemException, unfoundExecption;

    public Company getCompany(@Positive long companyId) throws unfoundExecption;

    public List<Company> getAllCompanies() throws unfoundExecption;

    public Customer getCustomer(@Positive long customerId) throws unfoundExecption;

    public List<Customer> getAllCustomers() throws unfoundExecption;

    public Coupon getCoupon(@Positive long couponId) throws CouponSystemException;

    public List<Coupon> GetAllCoupons() throws CouponSystemException;

    public Admin login(String name, String password, String email, ClientType clientType) throws LoginException;
}
