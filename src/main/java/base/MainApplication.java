package base;

import java.util.concurrent.Executors;

import base.server.BaseballServer;
import base.server.DefaultBaseBallServer;
import base.server.router.RequestRouter;
import base.server.router.SimpleRequestRouter;

public class MainApplication {

	public static void main(String[] args) {
		RequestRouter router = new SimpleRequestRouter();
		BaseballServer server = new DefaultBaseBallServer(Executors.newFixedThreadPool(50), router);
		server.start(8000);
		
	}
}
