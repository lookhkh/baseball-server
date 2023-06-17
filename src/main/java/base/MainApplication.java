package base;

import java.util.concurrent.Executors;

import base.server.BaseballServer;
import base.server.DefaultBaseBallServer;
import base.server.router.RequestRouter;
import base.server.router.SimpleRequestRouter;

public class MainApplication {

	public static void main(String[] args) {
		RequestRouter router = new SimpleRequestRouter(null);
		BaseballServer server = new DefaultBaseBallServer(Executors.newFixedThreadPool(50), router);
		server.start(8000);
		
		addShutDownHook(server);
	}

	private static void addShutDownHook(BaseballServer server) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("VM Process shut down and try to close the server");
				server.close();
			}
		});
	}
}
