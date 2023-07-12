package base.server.router.handler.impls;

import base.log.DefaultLogFormatter;
import base.server.router.handler.newGame.GameMatchingManager;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public class ReqNewGameHanlder extends DefaultGameHandler {
	
	private final GameMatchingManager manager;
	public ReqNewGameHanlder(UserRequest user, UserConnection con, GameMatchingManager manager) {
		super(con,user);
		this.manager = manager == null? GameMatchingManager.getGameMatchingManager():manager;
	}

	@Override
	public void handle(UserRequest req, UserConnection con) {
		DefaultLogFormatter.print("req new game");
		
		this.manager.handleNewUserRequest(con);
		
	}
	
	
}
