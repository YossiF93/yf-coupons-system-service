package backend.exceptions;

 
/**
 * <h3>Purchased Exception</h3> This Class is defying an exception that been thrown in the purchased process. 
 * 
*/
public class PurchesedExecption extends  CouponSystemException {
 
	private static final long serialVersionUID = 1L;

	public PurchesedExecption(String message) {
		super(message);
	}
	
}
