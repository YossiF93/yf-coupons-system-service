package backend;

import backend.dailyTask.DailyTask;
import backend.entities.Company;
import backend.entities.Coupon;
import backend.entities.Customer;
import backend.exceptions.CouponSystemException;
import backend.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * -- This Class is The Main Class Of The Program. --
 * -- In The Class There is an "Hard Coded" Example for showing the System Capabilities" --
 */

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@Slf4j
public class Application {

    public static void main(String[] args) throws CouponSystemException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        DailyTask dailyTask = (DailyTask) context.getBean("dailyTask");
        dailyTask.run();
        log.info("CouponSystem Starts - Good Luck");
    }

}
