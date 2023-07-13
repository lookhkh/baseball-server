package base.server.user.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public class BlockingUserConnection implements UserConnection {

	private final Socket socket;
	private BufferedReader r;
	private BufferedWriter w;

	public BlockingUserConnection(Socket socket) {
		this.socket = socket;
		try {
			this.r = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.w = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

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
		String returnStr = this.r.readLine();
		System.out.printf("%s send %s\n",this.socket.getRemoteSocketAddress(),returnStr );
		return new UserRequest( returnStr );
		
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

	@Override
	public void close() throws IOException {
		this.socket.close();
		this.r.close();
		this.w.close();
	}

	@Override
	public String toString() {
		return "con [socket=" + socket;
	}

	@Override
	public boolean isConnectionOkay() {
		return this.socket.isConnected();
	}
	
	
}
