package base.server.router.handler.newGame;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import base.log.DefaultLogFormatter;
import base.server.router.handler.newGame.vo.MatchingTuple;
import base.server.router.handler.newGame.vo.WatingPlayer;
import base.server.user.connection.UserConnection;

public class GameMatchingManager {
	
	private final ConcurrentLinkedQueue<WatingPlayer> lists;
	private final ScheduledExecutorService service;
	private final ExecutorService executor;
	private static GameMatchingManager manager = new GameMatchingManager(null, null);
	
	public static GameMatchingManager getGameMatchingManager() {
		return manager;
	}
	
	private GameMatchingManager(ConcurrentLinkedQueue<WatingPlayer> lists, ScheduledExecutorService service) {
		this.lists = lists==null? new ConcurrentLinkedQueue<>(): lists;
		this.service = service==null?Executors.newScheduledThreadPool(1):service;
		this.executor = Executors.newFixedThreadPool(100);
	}
	
	public boolean isAnyGameAvailable() {
		if(lists.isEmpty()) return false;
		return true;
	}
	
	public void joinWatingList(UserConnection userProxy) {
		
		WatingPlayer player = new WatingPlayer(userProxy);
		player.notifyWait(service);
		lists.add(player);
		
	}

	public void handleNewUserRequest(UserConnection con) {
		if(this.isAnyGameAvailable()) {
			WatingPlayer player =  this.lists.poll();
			player.cancelSchedule();
			DefaultLogFormatter.print(String.format("[%s] player find matching user against [%s]", con, player));
			
			MatchingTuple tuple = new MatchingTuple(player.getConnection(), con, executor);
			tuple.startNewGame();
			return;
		}
		
		this.joinWatingList(con);
		
		
	}
	
}
