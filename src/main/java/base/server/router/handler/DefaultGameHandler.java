package base.server.router.handler;

import java.io.IOException;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public abstract class DefaultGameHandler implements UserRequestHandler {

	private final UserConnection con;
	private final UserRequest req;
	public DefaultGameHandler(UserConnection con,UserRequest req) {
		this.con = con;
		this.req = req;
	}
	
	public void close() {
		try {
			this.con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	
	
}
