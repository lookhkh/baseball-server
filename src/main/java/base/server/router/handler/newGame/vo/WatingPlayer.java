package base.server.router.handler.newGame.vo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.UserResponse;

public class WatingPlayer {
	private final UserConnection userProxy;
	private final LocalDateTime time;
	private ScheduledFuture<?> schedule;
	
	public WatingPlayer(UserConnection userProxy) {
		this.userProxy = userProxy;
		this.time = LocalDateTime.now();
	}
	public void notifyWait(ScheduledExecutorService service) {
		this.schedule = service.scheduleAtFixedRate(()->{
			try {
				LocalDateTime now = LocalDateTime.now();
				Duration dur = Duration.between(time,now);
				this.userProxy.writeAndFlush(new UserResponse(String.format("Stil Wating ... [%d]", dur.getSeconds())));
			} catch (IOException e) {
				e.printStackTrace();
				this.schedule.cancel(true);
			}
		},0,1L, TimeUnit.SECONDS);
	}
	
	public void cancelSchedule() {
		this.schedule.cancel(true);
	}
	
	public UserConnection getConnection() {
		return this.userProxy;
	}

}
