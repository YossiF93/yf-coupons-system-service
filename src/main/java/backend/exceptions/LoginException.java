package backend.exceptions;
 

/**
 * <h3>Login Exception </h3> This Class is defying an exception that been thrown in the Login process. 
 * 
*/
public class LoginException extends CouponSystemException {
 
	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}
}

 