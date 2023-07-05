package base.server.router.handler.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import base.domains.UserInfo;

@Entity
public class UserInfoDto {

	@Id
	private String id;
	
	private String name;

	@Override
	public String toString() {
		return "UserInfoDto [id=" + id + ", name=" + name + "]";
	}

	public UserInfo toUserInfo() {
		UserInfo info = new UserInfo(this.id);
		
		return info;
	}
	
	
	
}
