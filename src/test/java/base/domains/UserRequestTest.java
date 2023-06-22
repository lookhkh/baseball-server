package base.domains;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.consts.UserConnectionErrors;
import base.server.user.connection.dto.UserRequest;

public class UserRequestTest {

	@Test
	@DisplayName("req 이후 요청자의 아이디를 보내지 않을 경우 에러를 반환한다.")
	public void test() {
		
		String invalidRequest = "req";
		
		assertThrows(RuntimeException.class,()->{
			 new UserRequest(invalidRequest);
		});
		
	}
	
	@Test
	@DisplayName("find 이후 대상자의 아이디를 보내지 않을 경우 에러를 반환한다.")
	public void test1() {
		
		String invalidRequest = "find";
		
		assertThrows(RuntimeException.class,()->{
			 new UserRequest(invalidRequest);
		});
		
	}
	
	@Test
	@DisplayName("find 이후 대상자의 아이디 사이에 공백이 없을 경우 에러를 반환한다.")
	public void test3() {
		
		String invalidRequest = "find123a";
		
		assertThrows(RuntimeException.class,()->{
			 new UserRequest(invalidRequest);
		});
		
	}
	
	@Test
	@DisplayName("find 이후 대상자의 아이디 사이에 공백이 있을 경우 에러를 반환하지 않는다.")
	public void test4() {
		
		String valid = "find 123a";
		
		assertDoesNotThrow(()->{
			 new UserRequest(valid);
		});
		
		String valid2 = "req 123a";
		
		assertDoesNotThrow(()->{
			 new UserRequest(valid2);
		});
		
		
	}
}
