package com.wtb.fuwu.wechart;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TokenUtils {
	private static long endTime = 0;
	private static String token = "";
	
	private static String tokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+StaticUtils.APP_ID_FW+"&secret="+StaticUtils.APP_SECRET_FW;
	
	public static String getToken() {
		JSONObject jobj = null;
		try {
			long currentTime = System.currentTimeMillis();
			if(currentTime > endTime) {
				System.out.println("获取Token..");
				HttpResponse resp = HttpUtils.doGet(tokenURL, new HashMap<String,String>(), new HashMap<String,String>());
				String res = EntityUtils.toString(resp.getEntity());
				jobj = new JSONObject(res);
				
				token = jobj.getString("access_token");
				long expiresIn = jobj.getLong("expires_in");
				endTime = currentTime+expiresIn*1000;
			}
		} catch (Exception e) {
			if(jobj != null) {
				int errcode = jobj.getInt("errcode");
				String errmsg = jobj.getString("errmsg");
				System.err.println("获取Token失败["+errcode+"]:"+errmsg);
			}else {
				System.err.println("获取Token失败:"+e.getMessage());
			}
		}
		return token;
	}
	
	public static void main(String[] args) {
		String token = TokenUtils.getToken();
		System.out.println(token);
	}
}
