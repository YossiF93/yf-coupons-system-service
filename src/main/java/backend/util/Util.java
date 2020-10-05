package backend.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import backend.exceptions.CouponSystemException;

 
/**
 * <h3>Util</h3> 
 *  This Class is A custom made Utils class .  
 *  This class is being used for Formatting Certain Date (From DB) / Getting the current date. 
 *  This class is being used In CompanyServiceImp.class as well as in CustomerServiceImp.class 
 */
public class Util {

	public static Date GetCurrentDate() {

		LocalDate now = LocalDate.now();
		Date CurrentDate = java.sql.Date.valueOf(now);

		return CurrentDate;
	}
	
	public static java.util.Date GetDBDate(Date DBdate) throws CouponSystemException {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date;
	date = null;
	try {
		date = format.parse(String.valueOf(DBdate));
	} catch (ParseException e) {
		throw new CouponSystemException("Error - Date Is Invalid");
	}
	return date;
}
	
	
	}
