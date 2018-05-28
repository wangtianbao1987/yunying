package com.wtb.fuwu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wtb.fuwu.data.DBUtils;
import com.wtb.fuwu.data.MyUtils;
import com.wtb.fuwu.wechart.SourceUtils;
import com.wtb.fuwu.wechart.StaticUtils;

@WebServlet("/queryOrder")
public class QueryOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String startDateStr = request.getParameter("startDate");
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		try {
			Long startTime = null;
			Long endTime = null;
			if(!MyUtils.isEmpty(startDateStr)) {
				Date startDate = StaticUtils.SF_DD.parse(startDateStr);
				startTime = startDate.getTime();
				endTime = startTime+24*3600000;
			}
			conn = DBUtils.getInstance().getConnection();
			String sql1 = "select id,createTime,payState,totPay,address,phone,userName,notLike from y_order where phone=?";
			if(startTime != null) {
				sql1 += " and createTime>="+startTime;
			}
			if(endTime != null) {
				sql1 += " and createTime<"+endTime;
			}
			sql1+=" order by createTime desc";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, phone);
			rs1 = pstmt1.executeQuery();
			
			String sql2 = "select id,name,price,num,idx,msg,orderId from y_order_item where orderId=? order by idx";
			pstmt2 = conn.prepareStatement(sql2);
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			boolean isFirst = true;
			while(rs1.next()) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("id", rs1.getString("id"));
				map1.put("createTime", rs1.getLong("createTime"));
				map1.put("payState", rs1.getInt("payState"));
				map1.put("totPay", rs1.getDouble("totPay"));
				map1.put("address", rs1.getString("address"));
				map1.put("phone", rs1.getString("phone"));
				map1.put("userName", rs1.getString("userName"));
				map1.put("notLike", rs1.getString("notLike"));
				
				if(isFirst) {
					pstmt2.setString(1, rs1.getString("id"));
					rs2 = pstmt2.executeQuery();
					List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
					while(rs2.next()) {
						Map<String,Object> map2 = new HashMap<String,Object>();
						map2.put("id", rs2.getLong("id"));
						map2.put("name", rs2.getString("name"));
						map2.put("price", rs2.getDouble("price"));
						map2.put("num", rs2.getDouble("num"));
						map2.put("idx", rs2.getInt("idx"));
						map2.put("msg", rs2.getString("msg"));
						map2.put("orderId", rs2.getString("orderId"));
						items.add(map2);
					}
					map1.put("items", items);
				}
				
				list.add(map1);
				isFirst = false;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("datas", list);
			MyUtils.succOut(map, response);
		} catch (Exception e) {
			e.printStackTrace();
			MyUtils.errOut("查询异常", response);
		} finally {
			SourceUtils.close(rs1,pstmt1,conn);
		}
	}

}
