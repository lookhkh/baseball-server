package base.server.router.handler.impls;

import base.log.DefaultLogFormatter;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public class ReqNewGameHanlder extends DefaultGameHandler {

	public ReqNewGameHanlder(UserRequest user, UserConnection con) {
		super(con,user);
	}

	@Override
	public void handle(UserRequest req, UserConnection con) {
		DefaultLogFormatter.print("req new game");
		
		this.close();
	}
	
	
}
