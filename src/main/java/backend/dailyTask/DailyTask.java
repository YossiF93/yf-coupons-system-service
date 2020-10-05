package backend.dailyTask;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import backend.repository.CouponJpaRepository;
 
/**
 * <h3>Daily Task</h3> This Class is defying the "Coupon Deleting Task" Required In the Project. 
 *  The Task is being run Automatically every 24 hours , On 00:01AM using CRON timeStamp.  
 *  The Task is Deleting Coupons that Out of stock / Expired .
 * 
*/

@Service 
public class DailyTask {

	@Autowired
	CouponJpaRepository couponJpaRepository ; 
	
	public void stop(){}
	
	@Scheduled( cron ="	0 1 0 1/1 * ? " )  
	public void run() {
		System.out.println("Daily Coupon System Task Is Running - Checking for Expierd Coupons");
		couponJpaRepository.RemoveExpierdCoupons(new Date(System.currentTimeMillis()) );
	
}}

