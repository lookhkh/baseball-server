package base.server.router.handler;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public class FindUserInfoHandler extends DefaultGameHandler {
	

	public FindUserInfoHandler(UserRequest user, UserConnection con) {
		super(con, user);
	}

	@Override
	public void handle(UserRequest req, UserConnection con) {
		System.out.println("find new User");
		this.close();
	}

	
}
