package base.server.user.connection.dto;

import java.util.Arrays;

import base.server.user.connection.UserConnection;

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
	
	private final String[] VALID_USER_REQ = new String[] {"REQ","FIND"};
	private final String original;
	private String type;
		
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
	}
	
	public String getReqType() {
		return this.type;
	}

	private boolean validateIfValidRequestType(String[] req) {
		for(int i=0; i<req.length; i++) {
			for(int j=0; j<VALID_USER_REQ.length; j++) {
				if(req[i].equalsIgnoreCase(VALID_USER_REQ[j])) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "UserRequest [VALID_USER_REQ=" + Arrays.toString(VALID_USER_REQ) + ", original=" + original + ", type="
				+ type + "]";
	}
}
