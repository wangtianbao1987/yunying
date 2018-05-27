package com.wtb.fuwu.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class MyUtils {
	public static void errOut(String err,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		map.put("message", err);
		JSONObject jobj = new JSONObject(map);
		String json = jobj.toString();
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	public static void succOut(Map<String,Object> map,HttpServletResponse response) throws IOException{
		if(map == null){
			map = new HashMap<String,Object>();
		}
		map.put("success", true);
		JSONObject jobj = new JSONObject(map);
		String json = jobj.toString();
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}
