package base.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import base.log.DefaultLogFormatter;
import base.server.router.RequestRouter;
import base.server.router.SimpleRequestRouter;
import base.server.user.connection.BlockingUserConnection;
import base.server.user.connection.ProxyUserConnection;

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
		
		DefaultLogFormatter.print("Start Server Socket");
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			
			while(!isClose || this.ex.isShutdown()) {
				
				Socket socket = serverSocket.accept();
				ex.submit(()->{
					DefaultLogFormatter.print("handle %s",socket.getInetAddress().getHostAddress());
					router.handle(new ProxyUserConnection(new BlockingUserConnection(socket)));	
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void close() {
		this.isClose = true;
		this.ex.shutdown();;
	}
}
