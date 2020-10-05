package backend.repository;

import backend.entities.Company;
import backend.entities.Coupon;
import backend.models.ClientType;
import backend.models.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * -- This Class is defying the Company Repository.
 * -- This class is Being Used By the JPA In Order to Bring Specific Data requested by custom Query's.
 */

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

    @Modifying
    @Query("update Company c set c.email = ?2 , c.password = ?3 where c.id = ?1")
    void updateCompany(long companyId, String Email, String Password);

    @Query("select c from Coupon c join c.company com where c.price<=?2 AND com.id = ?1")
    public List<Coupon> getCompanyCouponsByPrice(long companyId, double CopPrice);

    @Query("select c from Coupon c  join c.company com where c.type = ?2 AND com.id = ?1")
    public List<Coupon> getCompanyCouponsByType(long companyId, CouponType couponType);

    @Query("select c from Coupon c join c.company com where c.endDate <=?2 AND com.id = ?1")
    public List<Coupon> getCompanyCouponsByDate(long companyId, Date date);

    @Query("select c from Company c where c.name=?1 AND c.password=?2 AND c.email = ?3")
    public Company login(String name, String password, String email, ClientType clientType);

    @Query("select c from Coupon c join c.company com where com.id =?1")
    public List<Coupon> getCompanyCoupons(long CompanyId);

    @Query("select c from Coupon c join c.company com where com.id =?1 AND c.title=?2")
    public Coupon getCompanyCouponByTitle(long CompanyId, String title);

    @Query("select c from Company c where c.name=?1")
    public Company findByName(String companyName);

    @Query("select c from Company c where c.email=?1")
    public Company findByEmail(String email);

    public List<Company> findAll();

    public Company findById(long companyId);

    public void deleteById(long companyId);

}
