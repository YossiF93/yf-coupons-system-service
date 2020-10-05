package backend.service;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import backend.models.ClientType;
import backend.entities.Coupon;
import backend.models.CouponType;
import backend.entities.Customer;
import backend.util.Util;
import backend.validators.coupon.CouponValidator;
import backend.exceptions.CouponSystemException;
import backend.exceptions.PurchesedExecption;
import backend.exceptions.unfoundExecption;
import backend.repository.CouponJpaRepository;
import backend.repository.CustomerJpaRepository;

/**
 * <h3>Customer Service Implementation</h3> This Class Functions as a Service implementation
 * which implements CustomerService.class - Interface that represent All the Functions Available for a User from type - Customer.
 * 
*/
@Service
@Validated
 public class CustomerService implements ICustomerService {

	@Autowired
	private CouponJpaRepository couponJpaRepository;

	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	
	@Autowired
	private CouponValidator couponValidator;


	/**
	 * <h3>Purchased Coupon</h3> A method that receives a couponId and customerId and
	 * validates it and validates if the Expiration Date of the specific coupon is valid as well as the amount in stock.<br> 
	 * uses the CustomerRepository.class to check if the customer already been bought the same coupon in the past <br>
	 * if no - its adding the coupon to the customer coupon list and also update the amount of the coupon using the 
	 * CouponRepository.class.
	 * 
	 * @param couponId - couponId (long)
	 * @param customerId  - customerID (long)
	 * 
	 * @throws unfoundExecption - in case there is no Coupon/Customer with those Id's
	 *                                    in the Data Base. 
	 * @throws PurchesedExecption - in case Coupon is out of stock/Out of date/Coupon has been in the past
	 *                                    has been in the past.                                                                                                                       
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 *                                                                     
	 */
	public synchronized void PurchasedCoupon(long couponId, long customerId) throws CouponSystemException,PurchesedExecption,unfoundExecption {

		Optional<Customer> customer = couponValidator.toPurchasedCouponValidation(couponId, customerId);

		Coupon coupon = couponJpaRepository.findById(couponId);
			customer.get().getCoupons().add(coupon);
			customerJpaRepository.save(customer.get());
			coupon.setAmount(coupon.getAmount() - 1);
			coupon.getCustomers().add(customer.get());
			couponJpaRepository.save(coupon);
	}
	
	/**
	 * <h3>Get Customer Coupons</h3> A method that receives a customerId 
	 * validates it (in The Entity itself - Using "lombok" library) and uses the CustomerRepository.class to return a list of 
	 * the customer specified Coupons from the data base .
	 * 
	 * @param customerId  - customer id (long)
	 * @return coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCustomerCoupons(long customerId) throws unfoundExecption {

		List<Coupon> coupons = customerJpaRepository.getCustomerCoupons(customerId);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons found in the system");
		}

		return coupons;
	}

	/**
	 * <h3>Get Customer Coupons By Type</h3> A method that receives a customerId, coupon Type validates it <br> 
	 * (in The Entity itself - Using "lombok" library)
	 * and uses the CustomerRepository.class to return a list of 
	 * the customer specified by coupon type Coupons from the data base .
	 * 
	 * @param customerId  - customer ID (long)
	 * @param couponType  - couponType (CouponType.Enum)
	 * @return coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCustomerCouponsByType(long customerId, CouponType couponType) throws unfoundExecption {

		List<Coupon> coupons = customerJpaRepository.getCustomerCouponsByType(customerId, couponType);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons found in the system");
		}

		return coupons;
	}
	
	/**
	 * <h3>Get Customer Coupons By Price</h3> A method that receives a customerId ,certain Price -validates it <br> 
	 * (in The Entity itself - Using "lombok" library)
	 * and uses the CustomerRepository.class to return a list of 
	 * the customer specified by coupon type Coupons from the data base .
	 * 
	 * @param customerId  - customer ID (long)
	 * @param price  - Price (Double)
	 * @return coupons  - List of coupons 
	 * 
	 * @throws unfoundExecption - in case there is no Coupons found that matches with the giving Id's
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public List<Coupon> getCustomerCouponsByPrice(long customerId, double price) throws unfoundExecption {

		List<Coupon> coupons = customerJpaRepository.getCustomerCouponsByPrice(customerId, price);

		if (coupons.isEmpty()) {
			throw new unfoundExecption("Error - No Coupons found in the system");
		}
		return coupons;
	}

	/**
	 * <h3>Get Customer</h3> A method that receives a customerId 
	 * validates it and uses the CustomerRepository.class to return an object 
	 * of the customer specified from the data base .
	 * 
	 * @param customerId  - customer ID (long)
	 * @return Customer  - Customer (object)
	 * 
	 * @throws unfoundExecption - in case there is no Customer that matches with the giving Id's 
	 *                                    in the Data Base.                                                                                    
	 *                                                                     
	 */
	public Customer getCustomer(long customerId) throws unfoundExecption {

		Customer customer = customerJpaRepository.findById(customerId);
		if (customer == null) {
			throw new unfoundExecption("The Customer You Requested is not found in the system");
		}
		return customer;
	}

	
	/**
	 * <h3>Get Available Coupons</h3> A method that receives a customerId 
	 * validates it and uses the CouponRepository.class to bring all coupons in system. <br> and also uses CustomerRepository.class to identify 
	 * the coupons that the client already been purchased in the past. <br> 
	 *  then the method do a subtract action between the list and
	 * return a list of the available coupons for this specific client.
	 * 
	 * @param customerId  - customer ID (long)
	 *  @return coupons  - List of coupons  
	 * 
	 * @throws unfoundExecption - in case there is no coupons available for the client
	 *                                    in the Data Base.                                                                                    
	 * @throws CouponSystemException   - in case a there is no coupons in the system at all.
	 *                                                                      
	 */
	public List<Coupon> getAvailableCoupons(long customerId) throws CouponSystemException,unfoundExecption {

		List<Coupon> couponList = couponJpaRepository.findAll();
		if (couponList.isEmpty()) {
			throw new CouponSystemException("Error - No Coupons found in the system , Please Contect The Support.");
		}
 
		List<Coupon> customerCouponsList = customerJpaRepository.getCustomerCoupons(customerId);
		if (customerCouponsList.isEmpty()) {
			return couponList;
		}
		
          couponList.removeAll(customerCouponsList);
          List<Coupon> toRemove = new ArrayList<Coupon>();
          
          for (Coupon coupon : couponList) {
              if (coupon.getAmount() <= 0 || Util.GetCurrentDate().compareTo(coupon.getEndDate()) > 0 || Util.GetCurrentDate().compareTo(coupon.getStartDate()) < 0) {
                  toRemove.add(coupon);
              }
          }
          couponList.removeAll(toRemove);
 
		if(couponList.isEmpty()) {
			throw new unfoundExecption("Error - No Avilable Coupons found For You in the system.");
		}
		return couponList;
	}

	/**
	 * <h3>Customer Login</h3> A method that receives customer Login Parameters -validates it. <br> 
	 *  (in The Entity itself - Using "lombok" library)
	 *  and uses the CustomerRepository.class to validate the customer details<br> 
	 * if the validation process was successful - return the object of the customer, if not - Login Exception.
	 * 
	 * @param name  - name (String)
	 * @param password  - password (String)
	 * @param email  - email (String)
	 * @param clientType  - clientType (ClientType.enum)
	 * 
	 * @return Customer  - customer(object)
	 * 
	 * @throws LoginException - in case there is no Customer found that matches with the giving Id's
	 *                                    in the Data Base,Login Failed.                                                                                    
	 *                                                                     
	 */
	
	public Customer login(String name, String password, String email, ClientType clientType)
			throws LoginException {

		Customer customer = customerJpaRepository.login(name, password, email, clientType);
		if (customer != null) {
			return customer;
		} else {
			throw new LoginException("Error - Customer Login Failed");
		}

	}

}
