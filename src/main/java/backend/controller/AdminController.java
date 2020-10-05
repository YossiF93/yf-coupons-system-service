package backend.controller;


import java.util.List;

import backend.service.AdminService;
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
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;


/**
 * <h3>Admin Web Service</h3>
 * This Class is function as a "wrapper" REST API Controller of the AdminServiceImpl.class
 * This Class describe all the Functions that the Admin allowed in the system.
 * Each Function Has is one Path Which Can be Apply by the Client (Admin).
 *
 */
@RestController
@RequestMapping("REST/AdminWebService")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * <h3>Create Comapny</h3> A method that receives a company (JSON) from client in the Request Body - and creates a Company In the DB
     * using the AdminServiceImplantion.class
     * @param company - company (Object)
     * @return  HttpStatus - Http Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "CreateCompany", method = RequestMethod.POST)
    public ResponseEntity<String> CreateCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.createCompany(company);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /**
     * <h3>Remove Comapny</h3> A method that receives a company Id from client in as a request parameter - and removes the request Company In the DB
     * using the AdminServiceImplantion.class
     * @param companyId - companyId (long)
     * @return  HttpStatus - Http Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "RemoveCompany", method = RequestMethod.POST)
    public ResponseEntity<String> RemoveCompany(@RequestParam long companyId) throws CouponSystemException {
        adminService.removeCompany(companyId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /**
     * <h3>Update Company</h3> A method that receives a company (JSON) from client in the Request Body - and updates the Company In the DB
     * using the AdminServiceImplantion.class
     * @param company - company(Object)
     * @return  HttpStatus - Http Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "UpdateCompany", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /**
     * <h3>Create Customer</h3> A method that receives a customer (JSON) from client in the Request Body - and creates a Customer In the DB
     * using the AdminServiceImplantion.class
     * @param customer - customer(Object)
     * @return  HttpStatus - Http Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "CreateCustomer", method = RequestMethod.POST)
    public ResponseEntity<String> CreateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.createCustomer(customer);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /**
     * <h3>Remove Customer</h3> A method that receives a customer Id from client in as a request parameter - and removes the request Customer In the DB
     * using the AdminServiceImplantion.class
     * @param customerId - customerId(long)
     * @return  HttpStatus - Http Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "RemoveCustomer", method = RequestMethod.POST)
    public ResponseEntity<String> RemoveCustomer(@RequestParam long customerId) throws CouponSystemException {
        adminService.removeCustomer(customerId);
        return new ResponseEntity<String>(HttpStatus.OK);

    }

    /**
     * <h3>Update Customer</h3> A method that receives a customer (JSON) from client in the Request Body - and updates the Customer In the DB
     * using the AdminServiceImplantion.class
     * @param customer - Customer(Object)
     * @return  HttpStatus - Htttp Status
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "UpdateCustomer", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customer);
        return new ResponseEntity<String>(HttpStatus.OK);

    }

    /**
     * <h3>Get Company </h3> A method that receives a company Id from client in as a request parameter - and return the request Company from the DB
     * to the client using the AdminServiceImplantion.class
     * @param companyId - companyId (long)
     * @return  company - Company(Object)
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "GetCompany", method = RequestMethod.GET)
    public ResponseEntity<?> GetCompany(@RequestParam long companyId) throws CouponSystemException {
        Company company = adminService.getCompany(companyId);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    /**
     * <h3>Get Company </h3> A method that return all the companies exist in the DB
     * to the client using the AdminServiceImplantion.class
     * @return companylist - return list of companies
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "GetAllCompanies", method = RequestMethod.GET)
    public ResponseEntity<?> GetAllCompanies() throws CouponSystemException {
        List<Company> companylist = adminService.getAllCompanies();
        return new ResponseEntity<List<Company>>(companylist, HttpStatus.OK);
    }

    /**
     * <h3>Get Customer</h3> A method that receives a customer Id from client in as a request parameter - and return the request Customer from the DB
     * to the client using the AdminServiceImplantion.class
     * @param customerId - customerId(long)
     * @return customer - Customer(object)
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "GetCustomer", method = RequestMethod.GET)
    public ResponseEntity<?> GetCustomer(@RequestParam long customerId) throws CouponSystemException {
        Customer customer = adminService.getCustomer(customerId);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    /**
     * <h3>Get All Customers </h3> A method that return all the customers exist in the DB
     * to the client using the AdminServiceImplantion.class
     * @return  customerlist - return list of customers
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "GetAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<?> GetAllCustomers() throws CouponSystemException {
        List<Customer> customerlist = adminService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customerlist, HttpStatus.OK);
    }

    /**
     * <h3>Get Customer</h3> A method that receives a coupon Id from client in as a request parameter - and return the request Coupon from the DB
     * to the client using the AdminServiceImplantion.class
     * @param couponId - couponId (long)
     * @return coupon - Coupon(Object)
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */

    @RequestMapping(path = "GetCoupon", method = RequestMethod.GET)
    public ResponseEntity<?> GetCoupon(@RequestParam long couponId) throws CouponSystemException {
        Coupon coupon = adminService.getCoupon(couponId);
        return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
    }


    /**
     * <h3>Get All Coupons </h3> A method that return all the coupons exist in the DB
     * to the client using the AdminServiceImplantion.class
     * @return couponlist - return list of coupons
     * @throws CouponSystemException   - in case a running time Exception has been thrown/
     *                                   Unexpected Error has been occurred by the JPA.
     */
    @RequestMapping(path = "GetAllCoupons", method = RequestMethod.GET)
    public ResponseEntity<?> GetAllCoupons() throws CouponSystemException {
        List<Coupon> Couponlist = adminService.GetAllCoupons();
        return new ResponseEntity<List<Coupon>>(Couponlist, HttpStatus.OK);
    }

}

