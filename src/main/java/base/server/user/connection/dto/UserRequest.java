package base.server.user.connection.dto;

import java.util.Arrays;

import base.server.user.connection.UserConnection;
import base.server.user.connection.dto.util.ReqParam;

/*
 * 1. 게임생성
 * req userId
 * 
 * 2. 유저정보 조회
 * find targetUserId
 * 
 * 
 * */
public class UserRequest {
	
	private final String original;
	private String type;
	private String param;
	
	public UserRequest(String original) {
		this.original = original;
		
		String[] req = this.original.split(" ");
		
		if(req.length <= 1) {
			String invalidUserRequest = Arrays.stream(req).reduce("", (t1,t2)->t1+" "+t2);
			throw new RuntimeException(String.format("User parameter is Not valid. %s", invalidUserRequest));
		}
		
		if(!validateIfValidRequestType(req)) {
			throw new RuntimeException(String.format("User parameter is Not valid. %s", req[0]));
		};
		
		this.type = req[0];		
		this.param = req[1];
	}
	
	public String getReqType() {
		return this.type;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return this.param;
	}

	private boolean validateIfValidRequestType(String[] req) {
		for(int i=0; i<req.length; i++) {
			ReqParam[] VALID_USER_REQ = ReqParam.getParams();
			for(int j=0; j<VALID_USER_REQ.length; j++) {
				if(req[i].equalsIgnoreCase(VALID_USER_REQ[j].type)) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "UserRequest [original=" + original + ", type=" + type + ", param=" + param + "]";
	}

	
}

