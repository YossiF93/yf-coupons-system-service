package backend.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.models.Admin;
import backend.models.ClientType;
import backend.entities.Company;
import backend.entities.Coupon;
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;
import backend.exceptions.LoginException;
import backend.exceptions.unfoundExecption;
import backend.repository.CompanyJpaRepository;
import backend.repository.CouponJpaRepository;
import backend.repository.CustomerJpaRepository;
 
/**
 * <h3>Admin Service Implementation</h3> This Class Functions as a Service implementation
 * which implements AdminService.class - Interface that represent All the Functions Available for a User from type - Admin.
 * 
*/

@Service
public class AdminService implements IAdminService {

	@Autowired
	private CompanyJpaRepository companyJpaRepository;

	@Autowired
	private CustomerJpaRepository customerJpaRepository;

	@Autowired
	private CouponJpaRepository couponJpaRepository;

	
	/**
	 * <h3>Create Comapny</h3> A method that receives a company validates that there is no company with the same name/email
	 * in the system and then uses the CompanyRepository.Class to save 
	 * the company in the date base .
	 * 
	 * @param company - Company (company)
	 * 
	 * @throws unfoundExecption - in case there is Already Company with 
	 *                                   same name/email in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public void createCompany(Company company) throws CouponSystemException,unfoundExecption {

		Company companyCheck = companyJpaRepository.findByName(company.getName());

		if (companyCheck != null) {
			throw new unfoundExecption(
					"Error - Company " + company.getName() + " is Already Signed in the System,Cannot Create Company.");
		}

		Company companyCheck2 = companyJpaRepository.findByEmail(company.getEmail());

		if (companyCheck2 != null) {
			throw new unfoundExecption("Error - The Email " + company.getEmail()
					+ " is Already Signed in the System,Cannot Create Company.");
		}

		try {
			companyJpaRepository.save(company);

		} catch (Exception e) {
			throw new CouponSystemException("Error - Cannot create Company, Please Try Again Or Call Support.");
		}
	}

	
	/**
	 * <h3>Remove Company</h3> A method that receives a companyId 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CouponRepository.class to remove   
	 * the company coupons in the data base.  <br>  also uses the CompanyRepository.Class to remove
	 * the company itself from the data base as well as.
	 * 
	 * @param companyId  - company ID (long)
	 * 
	 * @throws unfoundExecption - in case there is no Company with those Id's
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public void removeCompany(long companyId) throws CouponSystemException,unfoundExecption {

		Company companyCheck = companyJpaRepository.findById(companyId);
		if (companyCheck == null) {
			throw new unfoundExecption("Error - Cannot remove this company ,Company dosent exist in the system");
		}

		try {
			couponJpaRepository.RemoveCompanyCoupons(companyId);
			companyJpaRepository.deleteById(companyId);

		} catch (Exception e) {
			throw new CouponSystemException("Error - Cannot remove Company, Please Try Again Or Call Support.");
		}
	}

	
	/**
	 * <h3>Update Company</h3> A method that receives a company object
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CompanyRepository.class to update 
	 * the company with the received new parameters (email, Password) in the data base.
 
	 * 
	 * @param company - Company(Object)
	 * 
	 * @throws unfoundExecption - in case there is no Company with that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	@Transactional
	public void updateCompany(Company company) throws CouponSystemException,unfoundExecption{

		Company companyCheck = companyJpaRepository.findById(company.getId());
		if (companyCheck == null) {
			throw new unfoundExecption(
					"Error - Cannot Update " + company.getName() + ",Company dosent exist in the system");
		}
		try {
			companyJpaRepository.updateCompany(company.getId(), company.getEmail(), company.getPassword());
		} catch (Exception e) {
			throw new CouponSystemException("Error - Cannot update Company, Please Try Again Or Call Support.");
		}

	}
	/**
	 * <h3>Get Company</h3> A method that receives a companyId 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CompanyRepository.class to return an object 
	 * of the company specified from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @return Company   - Company(object)
	 * 
	 * @throws unfoundExecption - in case there is no Company that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public Company getCompany(long companyId) throws unfoundExecption {

		Company company = companyJpaRepository.findById(companyId);
		if (company == null) {
			throw new unfoundExecption("Error - The Company You Requested is not found in the system");
		}
		return company;
	}

	/**
	 * <h3>Get All Companies</h3> A method that uses the CompanyRepository.class to return a list of 
	 * all the Companies are in the system from the data base .
	 * 
	 * @return Companies  - List of companies 
	 * 
	 * @throws unfoundExecption - in case there is no Companies found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Company> getAllCompanies() throws unfoundExecption {

		List<Company> companies = companyJpaRepository.findAll();
		if (companies.isEmpty()) {
			throw new unfoundExecption("Error - No Companies found in the system");
		}
		return companies;
	}

	
	/**
	 * <h3>Create Customer</h3> A method that receives a customer and validates that there is no customer with the same email
	 * in the system and then uses the CustomerRepository.Class to save 
	 * the customer  in the date base .
	 * 
	 * @param customer - Customer (object)
	 * 
	 * @throws unfoundExecption - in case there is Already Company with 
	 *                                   same name/email in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public void createCustomer(Customer customer) throws CouponSystemException,unfoundExecption {

		Customer customerCheck = customerJpaRepository.findByEmail(customer.getEmail());

		if (customerCheck != null) {
			throw new unfoundExecption("Error - The Email " + customer.getEmail()
					+ " is Already Signed in the System,Cannot Create Customer.");
		}
		try {
			customerJpaRepository.save(customer);
		} catch (Exception e) {
			throw new CouponSystemException("Error - Cannot create customer, Please Try Again Or Call Support.");
		}
	}

	
	/**
	 * <h3>Remove Customer</h3> A method that receives a customer Id 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CustomerRepository.class to remove   
	 * the coupons from the list of purchased coupons by the customer in the data base.  <br>  also uses the CustomreRepository.Class to remove
	 * the customer itself in the date base as well as.
	 * 
	 * @param customerId - customerId (long)
	 * 
	 * @throws unfoundExecption - in case there is no Customer with those Id's
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public void removeCustomer(long customerId) throws CouponSystemException,unfoundExecption {

		Customer customer = customerJpaRepository.findById(customerId);
		if (customer == null) {
			throw new unfoundExecption("Error - Cannot remove Customer , Customr Not found in the system.");

		}
		customer.getCoupons().clear();
		try {
			customerJpaRepository.save(customer);
			customerJpaRepository.deleteCustomerById(customerId);
		} catch (Exception e) {

			throw new CouponSystemException("Error - Cannot remove Customer , Please Try Again Or Call Support.");

		}
	}

	
	
	/**
	 * <h3>Update Customer</h3> A method that receives a customer object
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the Customerepository.class to update 
	 * the customer with the received new parameters (Password) in the data base.
 
	 * 
	 * @param customer - Customer (Object)
	 * 
	 * @throws unfoundExecption - in case there is no Customer with that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	@Transactional
	public void updateCustomer(Customer customer) throws CouponSystemException,unfoundExecption {

		Customer customerCheck = customerJpaRepository.findById(customer.getId());

		if (customerCheck == null) {
			throw new unfoundExecption("Customer Dosent found in the system");
		}
		try {
			customerJpaRepository.updateCustomer(customer.getId(), customer.getPassword());
		} catch (Exception e) {
			throw new CouponSystemException("Error - Cannot update Customer , Please Try Again Or Call Support.");

		}

	}

	
	/**
	 * <h3>Get Customer</h3> A method that receives a customerId 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CustomerRepository.class to return an object 
	 * of the customer specified from the data base .
	 * 
	 * @param customerId  - customer ID (long)
	 * @return Customer  - Customer(object)
	 * 
	 * @throws unfoundExecption - in case there is no Company that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public Customer getCustomer(long customerId) throws unfoundExecption {

		Customer customer = customerJpaRepository.findById(customerId);
		if (customer == null) {
			throw new unfoundExecption("Error - The Customer You Requested is not found in the system");
		}
		return customer;
	}

	
	
	/**
	 * <h3>Get All Customers</h3> A method that uses the CustomerRepository.class to return a list of 
	 * all the Customers are in the system from the data base .
	 * 
	 * @return Customers  - List of customers 
	 * 
	 * @throws unfoundExecption - in case there is no Customers found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Customer> getAllCustomers() throws unfoundExecption {

		List<Customer> customers = customerJpaRepository.findAll();
		if (customers.isEmpty()) {
			throw new unfoundExecption("Error - No Customers found in the system");
		}
		return customers;
	}

	
	/**
	 * <h3>Get Coupon</h3> A method that receives a couponId 
	 *  validates it (in The Entity itself - Using "lombok" library) <br> and uses the CouponRepository.class to return an object 
	 * of the coupon specified from the data base .
	 * 
	 * @param couponId  - coupon ID (long)
	 * @return Coupon  - Coupon(object)
	 * 
	 * @throws unfoundExecption - in case there is no Coupon that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public Coupon getCoupon(long couponId) throws unfoundExecption {

		Coupon coupon = couponJpaRepository.getCoupon(couponId);
		if (coupon == null) {
			throw new unfoundExecption("Error - The Coupon You Requested is not found in the system");
		}
		return coupon;
	}

	
	/**
	 * <h3>Get All Coupons</h3> A method that uses the CouponRepository.class to return a list of 
	 * all the Coupons are in the system from the data base .
	 * 
	 * @return Coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> GetAllCoupons() throws unfoundExecption {

		List<Coupon> coupons = couponJpaRepository.findAll();

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons found in  the system");
		}

		return coupons;
	}

	
	/**
	 * <h3>Company Login</h3> A method that receives Admin Login Parameters -validates it (in The Entity itself - Using "lombok" library)
	 *  and uses the default values of admin  to validate the admin details
	 * if the validation process was successful - return the object of the admin, if not - Login Exception.
	 * 
	 * @param name  - name (String)
	 * @param password  - password (String)
	 * @param email  - email (String)
	 * @param clientType  - clientType (ClientType.enum)
	 * 
	 * @return Admin  - Admin (object)
	 * 
	 * @throws LoginException - in case there is no admin found that matches with the giving Id's
	 *                                    in the Data Base,Login Failed.                                                                                    
	 *                                                                     
	 */
	public Admin login(String name, String password,String email, ClientType clientType) throws LoginException {
		Admin admin = new Admin(1, "Admin", "1234", "Admin@admin.com");

		if (name.equals("Admin") && password.equals("admin") && email.equals("admin@admin.com")) {
			return admin;
		} else {
			throw new LoginException("Admin Login Failed - Please Try Again");
		}

	}
}
