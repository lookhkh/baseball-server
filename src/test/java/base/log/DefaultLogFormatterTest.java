package base.log;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultLogFormatterTest {

	@Test
	@DisplayName("로그 호출 시 현재 시각과 쓰레드가 포함되어야 한다.")
	public void test() {
		String message = "TEMP";
		LocalDateTime cur = LocalDateTime.now();
		String now = DefaultLogFormatter.makeDateFormat(cur);
		String temp = DefaultLogFormatter.makeString(message,cur);
		String curThreadName = Thread.currentThread().getName();
		assertTrue(temp.contains(temp));
		assertTrue(temp.contains(now));
		assertTrue(temp.contains(curThreadName));
		

	}

	@Test
	@DisplayName("로그 호출 시 현재 시각과 쓰레드가 포함되어야 한다.")
	public void test2() {
		String message = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
		LocalDateTime cur = LocalDateTime.now();
		String now = DefaultLogFormatter.makeDateFormat(cur);
		String temp = DefaultLogFormatter.makeString(message,cur);
		String curThreadName = Thread.currentThread().getName();
		assertTrue(temp.contains(temp));
		assertTrue(temp.contains(now));
		assertTrue(temp.contains(curThreadName));
		

	}
}
