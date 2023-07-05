package base.server.user.connection.dto;

public class UserResponse {

	private final String msg;

	public UserResponse(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "UserResponse [msg=" + msg + "]";
	}
}
