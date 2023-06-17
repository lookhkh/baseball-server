package base.server.user.connection;

import java.io.IOException;

import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public interface UserConnection {
	
	public UserRequest read() throws IOException;
	public void writeAndFlush(UserResponse res) throws IOException;
	public void write(UserResponse res) throws IOException;
	public void flush() throws IOException;
	public void close() throws IOException;
}
