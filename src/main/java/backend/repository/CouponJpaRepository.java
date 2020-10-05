package backend.repository;

import java.sql.Date;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import backend.entities.Coupon;

/**
 * -- This Class is defying the Coupon Repository.
 * -- This class is Being Used By the JPA In Order to Bring Specific Data requested by custom Query's.
 */

@Repository
public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {

	@Modifying  
	@Query("update Coupon c set c.endDate = ?2, c.price = ?3 where c.id = ?1")
	public void updateCoupon(long couponId, Date endDate, Double price);

	@Query("select c from Coupon c where c.id =?1")
	public Coupon getCoupon(long copuonId);
	
	@Query("select c from Coupon c where c.title =?1")
	public Coupon getCouponByTitle(String title);
	
	@Transactional
	@Modifying
	@Query("delete from Coupon where endDate <=?1")
	public void RemoveExpierdCoupons(Date date);

	@Transactional
	@Modifying
	@Query("delete from Coupon where Company_id  =?1")
	public void RemoveCompanyCoupons(long companyId);

	public List<Coupon> findAll();

	public Coupon findById(long custId);

	public void deleteById(long id);

}
