package base.server.router.handler;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public interface UserRequestHandler {

	void handle(UserRequest req, UserConnection con);

}
