package base.server.router.handler.impls;

import java.util.Optional;

import base.domains.UserInfo;
import base.log.DefaultLogFormatter;
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
		DefaultLogFormatter.print("find new User");
		try {
			
			Optional<UserInfo> user = userRepo.findById(req.getUserId());
			UserResponse r = new UserResponse(user.isPresent()?String.format("UserInfo : %s", user.get()):String.format("No UserInfo about %s", req.getUserId()));
			con.writeAndFlush(r);
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	
}
