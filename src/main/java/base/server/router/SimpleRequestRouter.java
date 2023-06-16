package base.server.router;

import java.io.IOException;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserResponse;

public class SimpleRequestRouter implements RequestRouter {
	
	private String WELCOME_MSG = "Hello World!!\n";
	private String SERVE_MSG = "Whar Can i Do For You?\n";
	private String MENU;
	
	
	
	public SimpleRequestRouter() {
		MENU = "1. REQ_NEW_GAME\n"
			  +"2. GET USER INFORMATION\n";
		
	}

	@Override
	public void handle(UserConnection con) {
		System.out.printf("router get socket %s\n", con);
		try {
			
			printWelcomeAndServeMenu(con);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void printWelcomeAndServeMenu(UserConnection con) throws IOException {
		con.write(new UserResponse(WELCOME_MSG));
		con.write(new UserResponse(SERVE_MSG));
		con.write(new UserResponse(MENU));
		con.flush();
	}

	
}
