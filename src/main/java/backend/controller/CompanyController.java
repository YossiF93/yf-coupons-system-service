package backend.controller;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import backend.entities.Company;
import backend.entities.Coupon;
import backend.models.CouponType;
import backend.models.User;
import backend.exceptions.CouponSystemException;
import backend.service.CompanyService;
 
/**
 * <h3>Company Web Service</h3>  
 *  This Class is function as a "wrapper" REST API Controller of the CompanyServiceImpl.class  
 *  This Class describe all the Functions that the Company allowed in the system.
 *  Each Function Has is one Path Which Can be Apply by the Client (Company).  
 * 
*/
@RestController
@RequestMapping("REST/CompanyWebService")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * <h3>Create Coupon</h3> A method that receives a coupon (JSON) from client in the Request Body and the client details by the current Session - and creates a Coupon In the DB
	 * using the CompanyServiceImplantion.class 
	 * @param coupon - Coupon(Object)
	 * @param session - HttpSession(companyId)
	 * @return  HttpStatus - Http Status 
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "CreateCoupon", method = RequestMethod.POST)
	public ResponseEntity<String> CreateCoupon(HttpSession session, @RequestBody Coupon coupon)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		companyService.createCoupon(user.getId(), coupon);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	/**
	 * <h3>Remove Coupon</h3> A method that receives a coupon Id from client as a request parameter and the client details by the current Session - and remove the Coupon In the DB
	 * using the CompanyServiceImplantion.class 
	 * @param couponId - couponId(long)
	 * @param session - HttpSession(companyId)
	 * @return  HttpStatus - Http Status 
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "RemoveCoupon", method = RequestMethod.POST)
	public ResponseEntity<String> RemoveCoupon(HttpSession session, @RequestParam long couponId)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		companyService.removeCoupon(user.getId(), couponId);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	/**
	 * <h3>Update Coupon</h3> A method that receives a coupon (JSON) from client in the Request Body and the client details by the current Session - and updates the Coupon In the DB
	 * using the CompanyServiceImplantion.class 
	 * @param coupon - Coupon(Object)
	 * @param session - HttpSession(companyId)
	 * @return HttpStatus - Http Status 
	  * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "UpdateCoupon", method = RequestMethod.POST)
	public ResponseEntity<String> UpdateCoupon(HttpSession session, @RequestBody Coupon coupon)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		companyService.updateCoupon(user.getId(), coupon.getId(), coupon.getEndDate(), coupon.getPrice());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * <h3>Get Company</h3> A method that receives the client details by the current Session - and return an object of the logged company from the the DB
	 * using the CompanyServiceImplantion.class 
	 * @param session - HttpSession(companyId)
	 * @return comapny - Company(object)
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "GetCompany", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompany(HttpSession session) throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		Company company = companyService.getCompany(user.getId());
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	/**
	 * <h3>Get Company Coupons</h3> A method that receives the client details by the current Session - and return a list of the Company coupons from the the DB
	 * using the CompanyServiceImplantion.class 
	 * @param session - HttpSession(companyId)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "GetCompanyCoupons", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompanyCoupons(HttpSession session) throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = companyService.getCompanyCoupons(user.getId());
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);

	}

	/**
	 * <h3>Get Company Coupons By Type</h3> A method that receives a coupon type from client as a request parameter and the client details by the current Session - and return a list of the Company coupons by specific type from the the DB
	 * using the CompanyServiceImplantion.class 
	 * @param session - HttpSession(companyId)
	 * @param couponType - couponType (CouponType.enum)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	@RequestMapping(path = "GetCompanyCouponsByType", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompanyCouponsByType(HttpSession session, @RequestParam CouponType couponType)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = companyService.getCompanyCouponsByType(user.getId(), couponType);
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

	/**
	 * <h3>Get Company Coupons By Price</h3> A method that receives a price from client as a request parameter and the client details by the current Session - and return a list of the Company coupons by specific price from the the DB
	 * using the CompanyServiceImplantion.class 
	 * @param session - HttpSession(companyId)
	 * @param price - price(double)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "GetCompanyCouponsByPrice", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompanyCouponsByPrice(HttpSession session, @RequestParam double price)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = companyService.getCompanyCouponsByPrice(user.getId(), price);
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

	/**
	 * <h3>Get Company Coupons By Date</h3> A method that receives a date from client as a request parameter and the client details by the current Session - and return a list of the Company coupons by specific date from the the DB
	 * using the CompanyServiceImplantion.class 
	 * @param session - HttpSession(companyId)
	 * @param date - date(Date)
	 * @return couponlist - return list of coupons
	 * @throws CouponSystemException   - in case a running time Exception has been thrown/
	 *                                   Unexpected Error has been occurred by the JPA.
	 */
	
	@RequestMapping(path = "GetCompanyCouponsByDate", method = RequestMethod.GET)
	public ResponseEntity<?> GetCompanyCouponsByDate(HttpSession session, @RequestParam Date date)
			throws CouponSystemException {
		User user = (User) session.getAttribute("User");
		List<Coupon> couponlist = companyService.getCompanyCouponsByDate(user.getId(), date);
		return new ResponseEntity<List<Coupon>>(couponlist, HttpStatus.OK);
	}

}
