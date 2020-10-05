package backend.service;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.models.ClientType;
import backend.entities.Company;
import backend.entities.Coupon;
import backend.models.CouponType;
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;
import backend.exceptions.InvalidParamException;
import backend.exceptions.LoginException;
import backend.exceptions.unfoundExecption;
import backend.repository.CompanyJpaRepository;
import backend.repository.CouponJpaRepository;
import backend.repository.CustomerJpaRepository;
import backend.util.Util;
 
/**
 * <h3>Company Service Implementation</h3> This Class Functions as a Service implementation
 * which implements CompanyService.class - Interface that represent All the Functions Available for a User from type - Company.
 * 
*/

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyJpaRepository companyJpaRepository;

	@Autowired
	private CouponJpaRepository couponJpaRepository;

	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	
	/**
	 * <h3>Create Coupon</h3> A method that receives a coupon validates it (in The Entity itself - Using "lombok" library)
	 *  sets its company and uses the CouponRepository.Class to save
	 * it in the date base .
	 * 
	 * @param coupon - coupon Entity (coupon)
	 * @param companyId  - company ID (long)
	 * 
	 * 
	 * @throws InvalidParamException - in case the coupon expiration date
	 *                                   provided is not valid.
	 * @throws unfoundExecption - in case there is Already Coupon with 
	 *                                   same title in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public void createCoupon(long companyId, Coupon coupon) throws CouponSystemException,InvalidParamException,unfoundExecption {
		
		if(Util.GetDBDate(coupon.getStartDate()).after(Util.GetDBDate(coupon.getEndDate())) ) {
		 throw new InvalidParamException("Error - Coupon Dates Are Invalid,Expiration Date must Be After Creation Date"); 
		}
		
		Coupon couponCheck = couponJpaRepository.getCouponByTitle(coupon.getTitle());
		if (couponCheck != null) {
			throw new unfoundExecption("Error - Coupon is Already In the System, Cannot Create Coupon with the same title");

		}
		Coupon newCoupon;
		
		try {
			Company company = companyJpaRepository.findById(companyId);
			coupon.setCompany(company);
			newCoupon = couponJpaRepository.save(coupon);
			company.getCoupons().add(newCoupon);
			companyJpaRepository.save(company);
		} catch (Exception e) {

			throw new CouponSystemException("Error In Creating Coupon , Please Try again or Call Support");

		}

	}
	
	/**
	 <h3>Remove Coupon</h3> A method that receives a couponId and companyId 
	 * validates it (in The Entity itself - Using "lombok" library)  and uses the CustomerRepository.class to remove   
	 * the coupon from  the lists of purchased coupons by the customers in the data base.  <br>  also uses the CouponRepository.Class to remove
	 * the coupon itself in the date base as well as.
	 * 
	 * @param couponId - couponId (long)
	 * @param companyId  - company ID (long)
	 * 
	 * @throws unfoundExecption - in case there is no Coupon with those Id's
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	@Transactional
	public void removeCoupon(long companyId, long couponId) throws CouponSystemException,unfoundExecption {

		Coupon coupon = couponJpaRepository.getCoupon(couponId);

		if (coupon.getCompany().getId() != companyId || coupon == null) {
			throw new unfoundExecption("Error - Coupon didnt found in the system");
		}

		List<Customer> Customerlist = (List<Customer>) coupon.getCustomers();

		try {

			for (Customer customer : Customerlist) {
				customer.removeCoupon(coupon);
				customerJpaRepository.save(customer);
			}

			coupon.getCompany().removeCoupon(coupon);
			couponJpaRepository.delete(coupon);

		} catch (Exception e) {

			throw new CouponSystemException("Error In Removing Coupon , Please Try again or Call Support");

		}
	}
	
	/**
	 * <h3>Update Coupon</h3> A method that receives a couponId,companyId,EndDate,Price 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CouponRepository.class to update 
	 * the coupon with the received new parameters (Expiration date, Price) in the data base.
 
	 * 
	 * @param couponId - couponId (long)
	 * @param companyId  - company ID (long)
	 * @param endDate  - endDate (Date)
	 * @param price  - price (Double)
	 * 
	 * @throws unfoundExecption - in case there is no Coupon with that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	@Transactional
	public void updateCoupon(long companyId, long couponId, Date endDate, Double price) throws CouponSystemException,unfoundExecption {

		Coupon coupon = couponJpaRepository.findById(couponId);

		if (coupon.getCompany().getId() != companyId || coupon == null) {
			throw new unfoundExecption("Error - Coupon didnt found in the system");
		}

		try {
			couponJpaRepository.updateCoupon(couponId, endDate, price);
		} catch (Exception e) {

			throw new CouponSystemException("Error In Updating Coupon , Please Try again or Call Support");

		}
	}

	
	/**
	 * <h3>Get Company</h3> A method that receives a companyId 
	 * validates it (in The Entity itself - Using "lombok" library) <br> and uses the CompanyRepository.class to return an object 
	 * of the company specified from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @return Company  - Company (object)
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
	 * <h3>Get Company Coupons</h3> A method that receives a companyId 
	 *validates it (in The Entity itself - Using "lombok" library) <br> and uses the CompanyRepository.class to return a list of 
	 * the company specified Coupons from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @return Coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCompanyCoupons(long companyId) throws unfoundExecption {

		List<Coupon> coupons = companyJpaRepository.getCompanyCoupons(companyId);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons is not found in the system");
		}

		return coupons;
	}

	
	
	/**
	 * <h3>Get Company Coupons By Type</h3> A method that receives a companyId ,certain coupon Type -validates it (in The Entity itself - Using "lombok" library) <br>
	 * and uses the CompanyRepository.class to return a list of 
	 * the company specified by coupon type Coupons from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @param couponType  - couponType (CouponType.Enum)
	 * @return Coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCompanyCouponsByType(long companyId, CouponType couponType) throws unfoundExecption {

		List<Coupon> coupons = companyJpaRepository.getCompanyCouponsByType(companyId, couponType);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons is not found in the system");
		}

		return coupons;
	}

	
	/**
	 * <h3>Get Company Coupons By Price</h3> A method that receives a companyId ,certain price -validates it.<br> 
	 *  (in The Entity itself - Using "lombok" library)
	 *  and uses the CompanyRepository.class to return a list of 
	 * the company specified by price Coupons from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @param price  - price (Double)
	 * @return Coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCompanyCouponsByPrice(long companyId, double price) throws unfoundExecption {

		List<Coupon> coupons = companyJpaRepository.getCompanyCouponsByPrice(companyId, price);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons is not found in the system");
		}

		return coupons;
	}
	
	/**
	 * <h3>Get Company Coupons By Date</h3> A method that receives a companyId ,certain date -validates it.<br>  (in The Entity itself - Using "lombok" library)
	 *  and uses the CompanyRepository.class to return a list of 
	 * the company specified by date Coupons from the data base .
	 * 
	 * @param companyId  - company ID (long)
	 * @param date  - date (Date)
	 * @return Coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCompanyCouponsByDate(long companyId, Date date) throws unfoundExecption {

		List<Coupon> coupons = companyJpaRepository.getCompanyCouponsByDate(companyId, date);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons is not found in the system");
		}

		return coupons;
	}

	/**
	 * <h3>Company Login</h3> A method that receives company Login Parameters -validates it (in The Entity itself - Using "lombok" library)
	 *  and uses the CompanyRepository.class to validate the company details
	 * if the validation process was successful  -return the object of the company, if not - Login Exception.
	 * 
	 * @param name  - name (String)
	 * @param password  - password (String)
	 * @param email  - email (String)
	 * @param clientType  - clientType (ClientType.enum)
	 * 
	 * @return Company  - company
	 * 
	 * @throws LoginException - in case there is no Company found that matches with the giving Id's
	 *                                    in the Data Base,Login Failed.                                                                                    
	 *                                                                     
	 */
	public Company login(String name, String password, String email, ClientType clientType)
			throws LoginException {

		Company company = companyJpaRepository.login(name, password, email, clientType);

		if (company != null) {
			return company;
		} else {
			throw new LoginException("Error - Company Login Failed, Please Try Again");

		}
	}
}
