package base.server.router;

import base.server.user.connection.UserConnection;

public interface RequestRouter {
	public void handle(UserConnection socket);
}
