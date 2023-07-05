package base;

import java.util.concurrent.Executors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import base.log.DefaultLogFormatter;
import base.server.BaseballServer;
import base.server.DefaultBaseBallServer;
import base.server.router.RequestRouter;
import base.server.router.SimpleRequestRouter;
import base.server.router.handler.repo.impls.jpa.domains.UserInfoDto;

public class MainApplication {

	public static void main(String[] args) {
		warmUpJpaConnection();
		RequestRouter router = new SimpleRequestRouter(null);
		BaseballServer server = new DefaultBaseBallServer(Executors.newFixedThreadPool(50), router);
		server.start(8000);
		
		addShutDownHook(server);
		
	}

	private static void warmUpJpaConnection() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("user");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		tx.commit();
	}

	private static void addShutDownHook(BaseballServer server) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				DefaultLogFormatter.printError("VM Process shut down and try to close the server");
				server.close();
			}
		});
	}
}
