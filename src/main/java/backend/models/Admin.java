package backend.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * <h3>Admin</h3> 
 *  This Class is defying an object of a admin in the system (ENTITY). 
 *  "Lombok" Library Annotation Validation is being used in this class. 
 * 
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@NotNull(message="Error - Admin ID cannot be null")
	@Size(min=5,max=25) 
	private long id;
	
	@NotNull(message="Error - Admin Name cannot be NULL")
	@Size(min=5,max=25) 
	private String name;
	
	@NotNull(message="Error - Admin Password cannot be NULL")
	@Size(min=5,max=25) 
	private String password;
	
	@Email(message="Error - Admin Email is Not Valid")
	private String email;
 	
}
