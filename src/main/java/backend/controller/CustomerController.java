package backend.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import backend.entities.Coupon;
import backend.models.CouponType;
import backend.entities.Customer;
import backend.models.User;
import backend.exceptions.CouponSystemException;
import backend.service.CustomerService;
 
/**
 * <h3>Customer Web Service</h3>  
 *  This Class is function as a "wrapper" REST API Controller of the CustomerServiceImpl.class  
 *  This Class describe all the Functions that the Customer allowed in the system.
 *  Each Function Has is one Path Which Can be Apply by the Client (Customer)
 * 
*/
@RestController
@RequestMapping("/REST/CustomerWebService")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

 
	/**
	 * <h3>Purchased Coupon</h3> A method that receives couponId from client as a request parameter and the client details by the current Session and execute the purchased process
	 * using the CustomerServiceImplantion.class 
	 * 
	 * @param couponId - couponId(long)
	 * @param session - HttpSession(customerId)
	 * @return HttpStatus - HttpStatus 
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "PurchasedCoupon", method = RequestMethod.POST)
	public ResponseEntity<String> PurchesdCoupon(@RequestParam long couponId, HttpSession session)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		customerService.PurchasedCoupon(couponId, user.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * <h3>Get Customer</h3> A method that receives the client details by the current Session - and return an object of the logged customer from the DB
	 * using the CustomerServiceImplantion.class
	 * @param session - HttpSession(customerId) 
	 * @return customer - customer(Object)
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetCustomer", method = RequestMethod.GET)
	public ResponseEntity<?> GetCustomer(HttpSession session) throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		Customer customer = customerService.getCustomer(user.getId());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	/**
	 * <h3>Get Customer Coupons</h3> A method that receives the client details by the current Session - and return a list of the Customer coupons from the the DB
	 * using the CustomerServiceImplantion.class 
	 * @param session - HttpSession(customerId)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetCustomerCoupons", method = RequestMethod.GET)
	public ResponseEntity<?> GetCustomerCoupons(HttpSession session) throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = customerService.getCustomerCoupons(user.getId());
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

	/**
	 * <h3>Get Available Coupons</h3> A method that receives the client details by the current Session - and return a list of the Available coupons for purchased by the Customer from the the DB
	 * using the CustomerServiceImplantion.class 
	 * @param session - HttpSession(customerId)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetAvailableCoupons", method = RequestMethod.GET)
	public ResponseEntity<?> GetAvailableCoupons(HttpSession session) throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = customerService.getAvailableCoupons(user.getId());
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

	/**
	 * <h3>Get Customer Coupons By Type</h3> A method that receives a coupon type from client as a request parameter and the client details by the current Session - and return a list of the the Customer coupons by specific type from the the DB
	 * using the CustomerServiceImplantion.class 
	 * @param session - HttpSession(customerId)
	 * @param couponType - couponType(CouponType.enum)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetCustomerCouponsByType", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompanyCouponsByType(HttpSession session, @RequestParam CouponType couponType)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = customerService.getCustomerCouponsByType(user.getId(), couponType);
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

	/**
	 * <h3>Get Customer Coupons By Price</h3> A method that receives a price from client as a request parameter and the client details by the current Session - and return a list of the the Customer coupons by specific price from the the DB
	 * using the CustomerServiceImplantion.class 
	 * @param price - Price (String)
	 * @param session - HttpSession(customerId)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetCustomerCouponsByPrice", method = RequestMethod.GET)
	public ResponseEntity<?> GetCustomerCouponsByPrice(HttpSession session, @RequestParam double price)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = customerService.getCustomerCouponsByPrice(user.getId(), price);
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

}
