package base.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultLogFormatter {
	//TODO 외부 파일에서 로그 형식 가져오기
	protected static String makeString(String message, LocalDateTime date) {
		String curThreadName = Thread.currentThread().getName();
		LocalDateTime curTime = date == null? LocalDateTime.now():date;
		
		return String.format("[%s] [%s] %s",curThreadName,makeDateFormat(curTime),message);
	}
	
	protected static String makeDateFormat(LocalDateTime date) {
		return DateTimeFormatter.ISO_LOCAL_TIME.format(date);
	}
	
	public static void print(String message) {
		System.out.println(DefaultLogFormatter.makeString(message,null));
	}
	public static void print(String format, String ...messages) {
		String message = String.format(format, messages);
		System.out.println(DefaultLogFormatter.makeString(message,null));
	}

	public static void printError(String message) {
		System.err.println(DefaultLogFormatter.makeString(message,null));
	}
}
