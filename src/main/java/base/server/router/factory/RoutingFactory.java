package base.server.router.factory;

import base.server.router.handler.UserRequestHandler;
import base.server.user.connection.dto.UserRequest;

public interface RoutingFactory {

	public UserRequestHandler getHandler(UserRequest user);
	
}
