package base.server.user.connection.dto.util;

public enum ReqParam {
	REQ("REQ"),FIND("FIND"),NUMBER("NUMBER");
	
	public String type;
	private static ReqParam[] params;
	private ReqParam(String type) {
		this.type = type;
	}
	
	public static ReqParam[] getParams() {
		
		if(params == null) {
			return params = new ReqParam[] {ReqParam.FIND,ReqParam.REQ,ReqParam.NUMBER};
		}
		
		return params;
	}
}
