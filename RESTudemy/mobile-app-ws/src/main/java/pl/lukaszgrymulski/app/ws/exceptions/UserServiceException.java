package pl.lukaszgrymulski.app.ws.exceptions;

public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 341183837190812873L;

	public UserServiceException(String message) {
		super(message);
	}
}
