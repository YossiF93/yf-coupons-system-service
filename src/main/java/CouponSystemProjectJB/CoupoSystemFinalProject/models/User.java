package CouponSystemProjectJB.CoupoSystemFinalProject.models;

import CouponSystemProjectJB.CoupoSystemFinalProject.models.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * <h3>User</h3> 
 *  This Class is defying an object of a user the connecting in the system. 
 *  This class is being used mostly in the LoginService.Class 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private long id;
	private ClientType clientType;
	private String name;
	private String password;
	private String email;

}
