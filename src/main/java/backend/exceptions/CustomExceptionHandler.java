package backend.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import backend.models.ApiError;

/**
 * <h3>Custom Exception Handler </h3>  This Class is A Custom Exception Handler.  
 *The Handler is arranged By hierarchy and handle - Throwable.class , ConstraintViaolationException.class , CouponSystemException.class.
 *Each Exception throws his Own Massage (Saved In The API Error object) and being Transfer to the client.
*/

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleThrowable(Throwable t) {

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"Something wrong happened... Please contact the admin.");
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		List<String> errorMessages = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations) {
			errorMessages.add("Invalid value " + violation.getInvalidValue() + " for property "
					+ violation.getPropertyPath() + " : " + violation.getMessage());
		}
		 
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,errorMessages.size() + " violation errors occurred." + errorMessages);
	
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CouponSystemException.class)
	public ResponseEntity<Object> handleCouponSystemException(CouponSystemException e) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
 
}
