package base.domains;

public class UserInfo {

	private String userId;

	public UserInfo(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + "]";
	}
	
	
}
