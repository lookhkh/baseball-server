package base.server.user.connection;

import java.io.IOException;

import base.server.user.connection.dto.UserRequest;
import base.server.user.connection.dto.UserResponse;

public class ProxyUserConnection implements UserConnection {
	
	private final UserConnection proxy;
	
	public ProxyUserConnection(UserConnection proxy) {
		this.proxy = proxy;
	}

	@Override
	public UserRequest read() throws IOException {
		
		UserRequest req =  this.proxy.read();
		System.out.println(String.format("[%s] %s User Read %s", getCurrentThread() , this.proxy, req));
		return req;
	}

	private String getCurrentThread() {
		return Thread.currentThread().getName();
	}

	@Override
	public void writeAndFlush(UserResponse res) throws IOException {
		System.out.println(String.format("[%s] %s User write %s",getCurrentThread(), this.proxy, res));
		this.proxy.writeAndFlush(res);
	}

	@Override
	public void write(UserResponse res) throws IOException {
		System.out.println(String.format("[%s] %s User write %s", getCurrentThread(), this.proxy, res));
		this.proxy.write(res);
		
	}

	@Override
	public void flush() throws IOException {
		System.out.println(String.format("[%s] %s User flush", getCurrentThread(),this.proxy));
		this.proxy.flush();
	}

	@Override
	public void close() throws IOException {
		System.out.println(String.format("[%s] %s User close the socket", getCurrentThread(),this.proxy));
		this.proxy.close();
		
	}

	
}
