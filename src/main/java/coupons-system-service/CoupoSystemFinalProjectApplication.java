package CouponSystemProjectJB.CoupoSystemFinalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import CouponSystemProjectJB.CoupoSystemFinalProject.exceptions.CouponSystemException;

/**
 * -- This Class is The Main Class Of The Program. --
 *  -- In The Class There is an "Hard Coded" Example for showing the System Capabilities" --
 */

@SpringBootApplication

@ServletComponentScan
@EnableScheduling
public class CoupoSystemFinalProjectApplication {

	public static void main(String[] args) throws CouponSystemException {

		ConfigurableApplicationContext context = SpringApplication.run(CoupoSystemFinalProjectApplication.class ,args);
		System.out.println("Test Application Starts");
		System.out.println("Test Application Starts22");

		//This Lines Are For testing the System  - For VOL1 Project Check.
		//  You (Tavor) Can use some of the functions if you wish to.. (some of them are not updated to VOL 2&3).
		// Checking Admin Capiblities - Phase 1

		// DailyTask dailyTask = (DailyTask)context.getBean("dailyTask");

		// dailyTask.run();

		// AdminService AdminSer = (AdminService)context.getBean("adminServiceImpl");

		// System.out.println(AdminSer.getAllCompanies() );
		// System.out.println( AdminSer.getAllCustomers());
		// System.out.println( AdminSer.getCompany(54));
		// System.out.println(AdminSer.getCoupon(2));
		// System.out.println(AdminSer.getCustomer(2));

		/*
		 * 
		 * Customer customer1 = new Customer("David Blu", "ND1234",
		 * "DavidBlu@gmail.com"); Customer customer2 = new Customer("Derak Sharp",
		 * "JF5534", "DerarkSh@gmail.com"); Customer customer3 = new
		 * Customer("Michel Roll", "MJ2358", "MikeRoll@gmail.com"); Customer customer4 =
		 * new Customer("ForDelete", "M558", "DeleteMe@gmail.com");
		 * 
		 * Company company1 = new Company("SuperPharm", "SP5436",
		 * "info@SuperPharm.com"); Company company2 = new Company("Rikushet", "RJ9906",
		 * "info@Rikushet.com"); Company company3 = new Company("ForDelete", "43906",
		 * "DeleteMe@Rikushet.com");
		 * 
		 * AdminSer.createCompany(company1); AdminSer.createCompany(company2);
		 * AdminSer.createCompany(company3);
		 * 
		 * AdminSer.createCustomer(customer1); AdminSer.createCustomer(customer2);
		 * AdminSer.createCustomer(customer3); AdminSer.createCustomer(customer4);
		 */

		// Admin Check,After coupon being Created -Phase2

		// AdminSer.updateCompany("newEmail", "newPASS", 3);
		// AdminSer.updateCustomer("343", 3);
		// AdminSer.removeCompany(1);

		// AdminSer.removeCustomer(AdminSer.getCustomer(3));
		// System.out.println(AdminSer.getCustomer(2));

		/*
		 * System.out.println(AdminSer.getAllCompanies());
		 * System.out.println(AdminSer.getAllCustomers());
		 * System.out.println(AdminSer.getCompany(1)); System.out.println(
		 * AdminSer.getCustomer(1)); System.out.println(AdminSer.getCoupon(1));
		 */

		// Check For Errors
		// Company/Customer Couldnt Create Twice
		// AdminSer.createCompany(company1);
		// AdminSer.createCustomer(customer1);

		// CompanyService CompaSer =(CompanyService)
		// context.getBean("companyServiceImpl");

		// System.out.println(CompaSer.getCompany(2) );

		// System.out.println(CompaSer.getCompanyCouponsByDate(Date.valueOf("2019-08-01"),
		// 1));
		// System.out.println(CompaSer.getCompanyCouponsByPrice(100, 1));
		// System.out.println(CompaSer.getCompanyCouponsByType(CouponType.CLOTHING, 2));

		// Company a = CompaSer.login("SuperPharm", "SP5436", "info@SuperPharm.com",
		// ClientType.COMPANY);

		/*
		 * Coupon coupon1 = new Coupon( CouponType.HEALTH,"Ackmoll Pills",
		 * Date.valueOf("2019-01-01"), Date.valueOf("2019-10-01"),
		 * 3,"Great For Headecks!", 50, " -photo-"); Coupon coupon2 = new Coupon(
		 * CouponType.HEALTH,"Advill Pills", Date.valueOf("2019-01-01"),
		 * Date.valueOf("2019-08-01"), 10,"Great For Headecks!", 100, " -photo-");
		 * Coupon coupon3 = new Coupon( CouponType.HEALTH,"norupfhen Pills",
		 * Date.valueOf("2019-01-01"), Date.valueOf("2019-07-01"),
		 * 9,"Great For Headecks!", 250, " -photo-"); Coupon coupon4= new Coupon(
		 * CouponType.CAMPING,"Tent", Date.valueOf("2019-01-01"),
		 * Date.valueOf("2019-10-01"), 3,"Great Prodact!", 500, " -photo-"); Coupon
		 * coupon5 = new Coupon( CouponType.CLOTHING,"3 T-Shirts",
		 * Date.valueOf("2019-01-01"), Date.valueOf("2019-10-01"),2,"Best Shirts", 300,
		 * " -photo-"); Coupon coupon6 = new Coupon( CouponType.HEALTH,"3 T-Shirts",
		 * Date.valueOf("2019-01-01"), Date.valueOf("2019-10-01"), 1,"DeleteME", 300,
		 * " -photo-");
		 * 
		 * // System.out.println(coupon1.getCompany().getId());
		 * 
		 * 
		 * CompaSer.createCoupon( 1,coupon1); CompaSer.createCoupon( 1,coupon2);
		 * CompaSer.createCoupon( 1,coupon3); CompaSer.createCoupon( 2,coupon4);
		 * CompaSer.createCoupon( 2,coupon5); CompaSer.createCoupon( 2,coupon6);
		 */

		// Coupon coupon6 = new Coupon(AdminSer.getCompany(2) , CouponType.HEALTH,"3
		// T-Shirts", Date.valueOf("2019-01-01"), Date.valueOf("2019-10-01"),
		// 1,"DeleteME", 300, " -photo-");
		// CompaSer.createCoupon( coupon6,2);
		// System.out.println("-------------------------------");

		// CompaSer.updateCoupon(9,Date.valueOf("2022-03-01"), 34.0);
		// Before Removing Do Customer Check for Amount
		// CompaSer.removeCoupon(8);

		// System.out.println(AdminSer.getCoupon(1));
		/*
		 * System.out.println(CompaSer.getCompany(1));
		 * System.out.println("-------------------------------");
		 * 
		 * System.out.println(CompaSer.getCompanyCoupons(1));
		 * System.out.println("-------------------------------");
		 * 
		 * System.out.println(CompaSer.getCompanyCouponsByDate(Date.valueOf("2019-05-01"
		 * ), 1)); System.out.println("-------------------------------");
		 * 
		 * System.out.println(CompaSer.getCompanyCouponsByType(CouponType.HEALTH, 1));
		 * System.out.println("-------------------------------");
		 * 
		 * System.out.println(CompaSer.getCompanyCouponsByPrice(500, 1));
		 * System.out.println("-------------------------------");
		 */

		/*
		 * //Check For Errors //Company Couldnt Create Same Coupon Twice
		 * CompaSer.createCoupon( coupon1);
		 */

		// CustomerService CustSer= (CustomerService)
		// context.getBean("customerServiceImpl");

		// User a = CustSer.login("David Blu", "ND1234", "DavidBlu@gmail.com",
		// ClientType.CUSTOMER);
		// System.out.println(a);
		// System.out.println(CustSer.getCustomer(1));

		// System.out.println(CustSer.getCustomerCoupons(2));
		// System.out.println(CustSer.getCustomerCouponsByPrice(50, 2));
		// System.out.println(CustSer.getCustomerCouponsByType(CouponType.CAMPING,2));

		// Customer cust = CustSer.login("David Blu", "ND1234",
		// "DavidBlu@gmail.com",ClientType.CUSTOMER);

		// System.out.println(cust);
		// CustSer.PurchesdCoupon(8, 1);
		/*
		 * CustSer.PurchesdCoupon(2,1); CustSer.PurchesdCoupon(3, 2);
		 * CustSer.PurchesdCoupon(4,2); CustSer.PurchesdCoupon(5, 3);
		 * CustSer.PurchesdCoupon(6,3);
		 */
		/*
		 * System.out.println(CustSer.getCustomerCoupons(1) );
		 * System.out.println("-------------------------------");
		 * System.out.println(CustSer.getCustomerCouponsByPrice(300, 1));
		 * System.out.println("-------------------------------"); System.out.println(
		 * CustSer.getCustomerCouponsByType(CouponType.HEALTH, 1) );
		 * 
		 */

		/*
		 * //Check For Errors - Customers!
		 * 
		 * //Customer Cannot Buy Same Coupon CustSer.PurchesdCoupon(2, 1); //Amount
		 * Check CustSer.PurchesdCoupon(6, 1); CustSer.PurchesdCoupon(6, 2);
		 */

	}

}
