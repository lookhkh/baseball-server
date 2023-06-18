package base.server.router.handler.impls;

import java.io.IOException;

import base.domains.UserInfo;
import base.server.router.handler.repo.UserRepoFactory;
import base.server.router.handler.repo.UserRepository;
import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public class FindUserInfoHandler extends DefaultGameHandler {
	
	private final UserRepository userRepo;

	public FindUserInfoHandler(UserRequest user, UserConnection con) {
		super(con, user);
		this.userRepo = UserRepoFactory.getUserRepositiory();
	}
	
	public FindUserInfoHandler(UserRequest user, UserConnection con, UserRepository repo) {
		super(con, user);
		this.userRepo = repo;
	}

	@Override
	public void handle(UserRequest req, UserConnection con) {
		System.out.println("find new User");
		try {
			UserInfo user = userRepo.findById(req.getUserId());
			con.writeAndFlush(new UserResponse(String.format("UserInfo : %s", user)));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
				
	}

	
}
