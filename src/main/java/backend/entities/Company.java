package backend.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


/**
 * <h3>Company</h3> 
 *  This Class is defying an object of a Company in the system(ENTITY).  
 *  The JPA is building This Entity in the DB Automatically .
 *  "Lombok" Library Annotation Validations is being used in this class.  
 *   The Connection In The DB Between Company to her Customers is - One to Many.  
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Companies")
public class Company {

	@Id
	@NotNull(message = "Error - Company ID cannot be NULL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Error - Company Name cannot be NULL")
	@Size(min = 2, max = 25, message = "Company Name has to be between 2-25 characters")
	private String name;

	@NotNull(message = "Error - Company Password cannot be NULL")
	@Size(min = 5, max = 25, message = "Company Password has to be between 5-25 characters")
	private String password;

	@Email(message="Error - Company Email is Not Valid")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)   
	@Valid
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}

	public void removeCoupon(Coupon coupon) {
		coupons.remove(coupon);
	}
}
