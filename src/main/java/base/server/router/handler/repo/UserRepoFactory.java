package base.server.router.handler.repo;

import base.server.router.handler.repo.impls.jpa.JpaUserRepoImpl;

public class UserRepoFactory {
	
	private static final UserRepository repo;
	
	static {
		repo = new JpaUserRepoImpl();
	}
	
	public static UserRepository getUserRepositiory() {
		return repo;
	}
	
	
	
}
