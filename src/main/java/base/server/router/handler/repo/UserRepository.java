package base.server.router.handler.repo;

import base.domains.UserInfo;

public interface UserRepository {

	UserInfo findById(String userId);

	
}
