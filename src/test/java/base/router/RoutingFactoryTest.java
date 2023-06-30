package base.router;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.log.DefaultLogFormatter;
import base.server.router.factory.RoutingFactory;
import base.server.router.factory.SimpleRoutingFactoryImpl;
import base.server.router.handler.UserRequestHandler;
import base.server.router.handler.impls.FindUserInfoHandler;
import base.server.router.handler.impls.ReqNewGameHanlder;
import base.server.user.connection.dto.UserRequest;

public class RoutingFactoryTest {
	
	@Test
	@DisplayName("사용자가 새로운 게임을 요청하면 ReqNewGameHandler를 반환한다.")
	public void t() {
		
		RoutingFactory factory = new SimpleRoutingFactoryImpl();
		String type= "req";
		String userId= "temp";
		UserRequest req = new UserRequest(type+" "+userId);
		UserRequestHandler  handler = factory.getHandler(req, null);
		assertNotNull(handler);
		assertTrue(handler instanceof ReqNewGameHanlder);
	}
	
	@Test
	@DisplayName("사용자가 유저정보를 요청하면 FindUserInfoHandler를 반환한다.")
	public void t1() {
		
		RoutingFactory factory = new SimpleRoutingFactoryImpl();
		UserRequest req = getUserRequest();
		UserRequestHandler  handler = factory.getHandler(req, null);
		assertNotNull(handler);
		assertTrue(handler instanceof FindUserInfoHandler);
	}
	
	@Test
	@DisplayName("서로 다른 유저가 요청하면 각각 새로운 핸들러가 생성된다")
	public void t2() {
		RoutingFactory factory = new SimpleRoutingFactoryImpl();
		UserRequest req = getUserRequest();
		UserRequestHandler  handler = factory.getHandler(req, null);


		UserRequest req2 = getUserRequest();
		UserRequestHandler  handler2 = factory.getHandler(req2, null);
		
		assertTrue(handler != handler2);

	}

	private UserRequest getUserRequest() {
		String type= "find";
		String userId= "temp";
		UserRequest req = new UserRequest(type+" "+userId);
		return req;
	}
}
