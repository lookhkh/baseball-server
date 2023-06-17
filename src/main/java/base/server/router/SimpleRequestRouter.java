package base.server.router;

import java.io.IOException;
import static base.consts.UserConnectionErrors.INVALID_USER_INPUT;
import base.consts.UserConnectionErrors;
import base.server.router.factory.RoutingFactory;
import base.server.router.factory.SimpleRoutingFactoryImpl;
import base.server.router.handler.UserRequestHandler;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public class SimpleRequestRouter implements RequestRouter {
	
	private String WELCOME_MSG = "Hello World!!\n";
	private String SERVE_MSG = "Whar Can i Do For You?\n";
	private String MENU;
	private final RoutingFactory factory;
	
	public SimpleRequestRouter(RoutingFactory factory) {
		MENU = "1. REQ_NEW_GAME\n"
			  +"2. GET USER INFORMATION\n";
		this.factory = factory == null? new SimpleRoutingFactoryImpl() : factory;
	}

	@Override
	public void handle(UserConnection con) {
		System.out.printf("router get socket %s\n", con);
		try {
			printWelcomeAndServeMenu(con);
			while(true) {
				try {
					UserRequest req =  con.read();
					System.out.printf("[%s] get %s\n",Thread.currentThread().getName(),req.toString());
					
					UserRequestHandler handler =  factory.getHandler(req);
					
					handler.handle(req ,con);
					
					break;
				}catch(RuntimeException e) {
					e.printStackTrace();
					con.writeAndFlush(new UserResponse(INVALID_USER_INPUT));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			try {
				con.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
		
	}

	private void printWelcomeAndServeMenu(UserConnection con) throws IOException {
		con.write(new UserResponse(WELCOME_MSG));
		con.write(new UserResponse(SERVE_MSG));
		con.write(new UserResponse(MENU));
		con.flush();
	}

	
}
