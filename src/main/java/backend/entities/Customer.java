package backend.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * <h3>Customer</h3> 
 *  This Class is defying an object of a Customer in the system. 
 *  The JPA is building This Entity in the DB Automatically. 
 *  "Lombok" Library Annotion Validaion is being used in this class.  
 *  The Connection In The DB Between Customer To his Coupon is - Many to Many. 
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers")
public class Customer {
	
	@Id
	@NotNull(message = "Error - Customer ID cannot be NULL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 3, max = 25, message = "Error - Customer Name has to be between 3-25 characters")
	private String name;
	
	@NotNull
	@Size(min = 5, max = 25, message = "Error - Customer Password to be between 5-25 characters")
	private String password;
	
	@Email(message="Error -Customer Email is Not Valid")
	private String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Valid
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}

	public void removeCoupon(Coupon coupon) {
		coupons.remove(coupon);
	}

}
