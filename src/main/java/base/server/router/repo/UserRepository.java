package base.server.router.repo;

import java.util.Optional;

import base.server.user.connection.dto.user.UserInfo;

public interface UserRepository {

	Optional<UserInfo> findById(String userId);

	
}
