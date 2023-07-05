package base.server.router.handler.repo;

import java.util.Optional;

import base.domains.UserInfo;

public interface UserRepository {

	Optional<UserInfo> findById(String userId);

	
}
