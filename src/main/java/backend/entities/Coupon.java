package backend.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import backend.models.CouponType;
import lombok.*;

/**
 * <h3>Coupon</h3> 
 *  This Class is defying an object of a Coupon in the system. 
 *  The JPA is building This Entity in the DB Automatically. 
 *  "Lombok" Library Annotion Validaion is being used in this class. 
 *  The Connection In The DB Between Coupon To Customers - Many To many. 
 *  The Connection In The DB Between Coupon To Company - Many To one. 
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Error - Coupon ID cannot be null")
	private long id;

	@JsonIgnore
	@ManyToMany(mappedBy = "coupons", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Customer> customers = new ArrayList<Customer>();

	@JsonIgnore
	@ManyToOne
	@Valid
	private Company company;

	@Enumerated(EnumType.STRING)
	private CouponType type;

	@NotNull(message = "Error - Coupon title cannot be NULL")
	@Size(min = 3, max = 25, message = "Coupon title has to be between 3-25 characters")
	private String title;

	private Date startDate;

	private Date endDate;

	@Positive(message = "Error - Coupon amount cannot be less then 0")
	private int amount;

	@NotNull(message = "Error - Coupon message cannot be NULL")
	private String message;

	@Positive(message = "Error - Coupon price cannot be less then 0")
	private double price;

	@NotNull(message = "Error - image title cannot be NULL")
	@Size(min = 2, max = 1000, message = "Coupon image url has to be between 2-1000 characters")
	private String image;


}
