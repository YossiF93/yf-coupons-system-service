package backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.models.ClientType;
import backend.entities.Coupon;
import backend.models.CouponType;
import backend.entities.Customer;
/**
 * -- This Class is defying the Customer Repository.
 * -- This class is Being Used By the JPA In Order to Bring Specific Data requested by custom Query's.
 */
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long>{

	@Modifying
	@Query("update Customer c set c.password = ?2  where c.id = ?1")
	public void updateCustomer( long customerId,String Password);
	 
	@Query("select c from Customer cust join cust.coupons c where cust.id = ?1")
	public List<Coupon> getCustomerCoupons(long id);

	@Query("select c from Customer cust join cust.coupons c where c.type=?2 AND cust.id = ?1")
	public List<Coupon> getCustomerCouponsByType( long id,CouponType couponType);

	@Query("select c from Customer cust join cust.coupons c where c.price<=?2 AND cust.id = ?1")
	public List<Coupon> getCustomerCouponsByPrice( long id,double copPrice);

	@Query("select c from Customer c where c.name=?1 AND c.password=?2 AND c.email = ?3")
	public Customer login(String name, String password, String email, ClientType clientType);
	
	@Query("select c from Customer c where c.email = ?1")
	public Customer findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("delete from Customer c where c.id  =?1")
	public void deleteCustomerById(long customerId);
	
	public Customer findById(long CustId);

	public void  deleteById(long id);
	
	public List<Customer>findAll();
		
			
}
 
