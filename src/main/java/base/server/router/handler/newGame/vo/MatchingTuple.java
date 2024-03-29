package base.server.router.handler.newGame.vo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import base.log.DefaultLogFormatter;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;
import base.server.user.connection.dto.util.ReqParam;

public class MatchingTuple {

	private final List<UserConnection> lists;
	private final ExecutorService service;

	public MatchingTuple(UserConnection user1, UserConnection user2, ExecutorService service) {
		this.service = service;
		this.lists = Arrays.asList(user1, user2);
		this.lists.forEach(t->{
			try {
				t.writeAndFlush(new UserResponse("New Game!!"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void notifyGameOver() {
		this.lists.forEach(t->{
			try {
				t.writeAndFlush(new UserResponse("Game Over!!\n"));
				t.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}

	public void startNewGame() {
		
		this.requestEachNumber();
	}

	private void requestEachNumber() {
		
		this.lists.forEach(t->{
			
			this.service.submit(()->{
				while(true) {
					try {
						t.writeAndFlush(new UserResponse("What is Your number?"));
						UserRequest numberPerEach = t.read();
						
						DefaultLogFormatter.print(numberPerEach);

						if(isProperParam(numberPerEach)) {
							
						}else {
							System.out.println("2");
							continue;
						}
						
						
						break;
					}catch(IOException e) {
						e.printStackTrace();
						handleIfConnectionSet();
						break;
					}
				}
			});
			
		});
	
	}

	private boolean isProperParam(UserRequest req) {
//		if(req.getReqType().equals(ReqParam.NUMBER.type)) return true;
//		return false;
		return true;
	}

	private void handleIfConnectionSet() {
		
		this.lists.stream()
				  .filter(t->t.isConnectionOkay())
				  .forEach(t->{
					try {
						t.writeAndFlush(new UserResponse("You Lost your counter part."));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}
	
}
