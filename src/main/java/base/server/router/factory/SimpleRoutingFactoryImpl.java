package base.server.router.factory;

import base.server.router.handler.UserRequestHandler;
import base.server.router.handler.impls.FindUserInfoHandler;
import base.server.router.handler.impls.ReqNewGameHanlder;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;

public class SimpleRoutingFactoryImpl implements RoutingFactory {

	@Override
	public UserRequestHandler getHandler(UserRequest user, UserConnection con) {
		
		if(user.getReqType().equalsIgnoreCase("req")) {
			return new ReqNewGameHanlder(user, con, null);
		}
		
		if(user.getReqType().equalsIgnoreCase("find")) {
			return new FindUserInfoHandler(user, con);
		}
		
		return null;
	}

}
