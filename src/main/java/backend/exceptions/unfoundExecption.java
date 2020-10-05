package backend.exceptions;
 
/**
 * <h3>UnFound Exception </h3> This Class is defying an exception that been thrown when an object is Unfound / Found but not supposed to be found. 
 * 
*/
public class unfoundExecption extends CouponSystemException {
 
	private static final long serialVersionUID = 1L;

	public unfoundExecption(String message) {
		super(message);
	}
	
}
