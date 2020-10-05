package backend.exceptions;

/**
 * <h3>Invalid Parameter Exception </h3> This Class is defying an exception that been thrown when an object is Invalid. 
 * 
*/
public class InvalidParamException extends CouponSystemException {
 
	private static final long serialVersionUID = 1L;

	public InvalidParamException(String message) {
		super(message);
	}
}
