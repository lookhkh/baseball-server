package base.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import base.server.router.RequestRouter;
import base.server.router.SimpleRequestRouter;
import base.server.user.connection.BlockingUserConnection;

public class DefaultBaseBallServer implements BaseballServer {
	
	private boolean isClose;
	private final ExecutorService ex;
	private final RequestRouter router;
	public DefaultBaseBallServer(ExecutorService ex, RequestRouter router) {
		this.isClose = false;
		this.ex = ex;
		this.router = router;
	}

	public void start(int port) {
		
		System.out.println("Start Server Socket");
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			
			while(!isClose || this.ex.isShutdown()) {
				
				Socket socket = serverSocket.accept();
				ex.submit(()->{
					System.out.printf("%s handle %s",Thread.currentThread().getName(),socket.getInetAddress().getHostAddress());
					router.handle(new BlockingUserConnection(socket));	
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void close() {
		this.isClose = true;
		this.ex.shutdown();
	}
}
