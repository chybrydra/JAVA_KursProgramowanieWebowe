package pl.lukaszgrymulski.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

	
	@NotNull(message="First name cannot be null!")
	@Size(min=3, max=20, message="First name must be 3 to 20 characters long!")
	private String firstName;
	
	@NotNull (message="Last name cannot be null!")
	private String lastName;
	
	@NotNull (message="Email cannot be null!")	
	@Email ()
	private String email;
	
	@NotNull (message="Password cannot be null!")
	@Size(min=8,max=16,message="Password must be 8 to 16 characters long")
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
