package base.server.router.handler.repo.impls;

import base.domains.UserInfo;
import base.server.router.handler.repo.UserRepository;

public class SimpleUserRepositoryImpl implements UserRepository {
	
	
	@Override
	public UserInfo findById(String userId) {
		
		return new UserInfo("mock");
	}

	
}
