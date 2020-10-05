package backend.models;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import lombok.Data;
 
/**
 * <h3>Api Error</h3> 
 *   This Class is defying an object of an API Error - Which will be send to the Client. 
 *   This Object is being used mostly in the ExceptionHandler.Class 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private HttpStatus status;
	private List<String> messages;

	public ApiError(HttpStatus status, String... messages) {
		super();
		this.status = status;
		this.messages = Arrays.asList(messages);
	}

	public ApiError(String string, List<String> errorMessages) {
		// TODO Auto-generated constructor stub
	}


}