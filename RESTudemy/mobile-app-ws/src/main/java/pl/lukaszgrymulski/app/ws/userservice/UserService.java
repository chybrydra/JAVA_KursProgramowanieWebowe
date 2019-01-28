package pl.lukaszgrymulski.app.ws.userservice;

import pl.lukaszgrymulski.app.ws.ui.model.request.UserDetailsRequestModel;
import pl.lukaszgrymulski.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
