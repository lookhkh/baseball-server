package base.server.router.repo.impls.jpa.domains;

import javax.persistence.Entity;
import javax.persistence.Id;

import base.server.user.connection.dto.user.UserInfo;

@Entity
public class UserInfoDto {
	@Id
	private String id;
	
	@Override
	public String toString() {
		return "UserInfoDto [id=" + id + "]";
	}

	public UserInfo toUserInfo() {
		return new UserInfo(this.id);
	}
	
}
