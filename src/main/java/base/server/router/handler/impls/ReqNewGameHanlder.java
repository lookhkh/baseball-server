package base.server.router.handler.impls;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public class ReqNewGameHanlder extends DefaultGameHandler {

	public ReqNewGameHanlder(UserRequest user, UserConnection con) {
		super(con,user);
	}

	@Override
	public void handle(UserRequest req, UserConnection con) {
		System.out.println("req new game");
		
		this.close();
	}
	
	
}
