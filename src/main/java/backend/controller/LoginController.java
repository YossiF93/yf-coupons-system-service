package backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Admin;
import backend.models.ClientType;
import backend.entities.Company;
import backend.entities.Customer;
import backend.models.User;
import backend.exceptions.CouponSystemException;
import backend.service.AdminService;
import backend.service.CompanyService;
import backend.service.CustomerService;
 
/**
 * <h3>Login Service</h3> 
 *  This Class is function as The Login Web Service . 
 *  Each client that request to Log in to system being checked by his ID details Via The Login Function In the ServiceImpl Classes. 
 *  Each Client that request to Log Out of the system - The Session is Being Killed By The LogOut method.
 * 
*/
@RestController
@RequestMapping("Login/*")
public class LoginController {

	//private static final long serialVersionUID = 1L;
	@Autowired
	private AdminService AdminService;
	@Autowired
	private CompanyService CompanyService;
	@Autowired
	private CustomerService CustomerService;

	@RequestMapping(path = "login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User loggedUser, HttpServletRequest request)
			throws CouponSystemException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}
		session = request.getSession(true);

		switch (loggedUser.getClientType()) {

		case ADMIN:
			Admin admin = AdminService.login(loggedUser.getName(), loggedUser.getPassword(),loggedUser.getEmail(), 
					loggedUser.getClientType());
			if (admin != null) {
				User user = new User(admin.getId(), ClientType.ADMIN, admin.getName(), admin.getPassword(),admin.getEmail());
				session.setAttribute("User", user);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			break;

		case COMPANY:
			Company company = CompanyService.login(loggedUser.getName(), loggedUser.getPassword(),
					loggedUser.getEmail(), loggedUser.getClientType());

			if (company != null) {
				User user = new User(company.getId(), ClientType.COMPANY, company.getName(), company.getPassword(),company.getEmail());
				session.setAttribute("User", user);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			break;

		case CUSTOMER:
			Customer customer = CustomerService.login(loggedUser.getName(), loggedUser.getPassword(),
					loggedUser.getEmail(), loggedUser.getClientType());

			if (customer != null) {
				User user = new User(customer.getId(), ClientType.CUSTOMER, customer.getName(), customer.getPassword(),customer.getEmail());
				session.setAttribute("User", user);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			break;
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);

	}

	@RequestMapping(path = "LogOut", method = RequestMethod.GET)
	public ResponseEntity<?> LogOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
