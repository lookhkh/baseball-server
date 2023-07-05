package base.server.router.handler.repo.impls;

import java.util.Optional;

import base.domains.UserInfo;
import base.server.router.handler.repo.UserRepository;

public class SimpleUserRepositoryImpl implements UserRepository {
	
	
	@Override
	public Optional<UserInfo> findById(String userId) {
		
		return Optional.of(new UserInfo("mock"));
	}

	
}
