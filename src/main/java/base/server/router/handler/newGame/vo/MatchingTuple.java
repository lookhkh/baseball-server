package base.server.router.handler.newGame.vo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserResponse;

public class MatchingTuple {

	private final List<UserConnection> lists;

	public MatchingTuple(UserConnection user1, UserConnection user2) {
		super();
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
	
}
