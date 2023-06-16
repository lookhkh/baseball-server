package base.server.user.connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public class BlockingUserConnection implements UserConnection {

	private final Socket socket;
	private InputStreamReader r;
	private OutputStreamWriter w;

	public BlockingUserConnection(Socket socket) {
		this.socket = socket;
		try {
			this.r = new InputStreamReader(this.socket.getInputStream());
			this.w = new OutputStreamWriter(this.socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
			
			try {
				System.err.printf("사용자 소켓 생성 중 에러 발생 %s",e.getMessage());
				this.socket.close();
				
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
	}

	@Override
	public UserRequest read() throws IOException {
		return null;
	}

	@Override
	public void write(UserResponse res) throws IOException {
		this.w.write(res.getMsg());
	}

	@Override
	public void writeAndFlush(UserResponse res) throws IOException {
		this.write(res);
		this.flush();
	}

	@Override
	public void flush() throws IOException {
		this.w.flush();
	}
}
