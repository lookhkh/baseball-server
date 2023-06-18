package base.server.router.handler.repo;

import base.server.router.handler.repo.impls.SimpleUserRepositoryImpl;

public class UserRepoFactory {
	
	private static final UserRepository repo;
	
	static {
		repo = new SimpleUserRepositoryImpl();
	}
	
	public static UserRepository getUserRepositiory() {
		return repo;
	}
	
	
	
}
