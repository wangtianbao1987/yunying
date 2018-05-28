package com.wtb.fuwu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wtb.fuwu.data.DBUtils;
import com.wtb.fuwu.data.MyUtils;
import com.wtb.fuwu.wechart.SourceUtils;

@WebServlet("/saveOrder")
public class SaveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		String winId = request.getParameter("winId");
		
		HttpSession session = request.getSession();
		List<Map<String,Object>> items = (List<Map<String,Object>>)session.getAttribute(winId);
		if(items == null){
			MyUtils.errOut("订购已失效,请返回重新添加", response);
			return;
		}
		
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String notLike = request.getParameter("notLike");
		
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql1 = "insert into y_order(id,createTime,payState,totPay,address,phone,userName,notLike)values(?,?,?,?,?,?,?,?)";
		String sql2 = "insert into y_order_item(name,price,num,idx,orderId)values(?,?,?,?,?)";
		String orderId = UUID.randomUUID().toString();
		
		try {
			conn = DBUtils.getInstance().getConnection();
			DBUtils.setAutoCommit(conn, false);
			pstmt2 = conn.prepareStatement(sql2);
			double totPay = 0;
			for(int i=0;i<items.size();i++){
				Map<String,Object> map = items.get(i);
				double price = (double)map.get("price");
				double num = Double.parseDouble(map.get("count").toString());
				String idx = map.get("idx").toString();
				String name = map.get("name").toString();
				pstmt2.setString(1, name);
				pstmt2.setDouble(2, price);
				pstmt2.setDouble(3, num);
				pstmt2.setString(4, idx);
				pstmt2.setString(5, orderId);
				pstmt2.addBatch();
				totPay+=(price*num);
			}
			pstmt2.executeBatch();
			
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, orderId);
			pstmt1.setLong(2, System.currentTimeMillis());
			pstmt1.setInt(3, 0);
			pstmt1.setDouble(4, totPay);
			pstmt1.setString(5, address);
			pstmt1.setString(6, phone);
			pstmt1.setString(7, userName);
			pstmt1.setString(8, notLike);
			pstmt1.executeUpdate();
			conn.commit();
			session.removeAttribute(winId);
			MyUtils.succOut(null, response);
		} catch (Exception e) {
			MyUtils.errOut("订单信息保存失败", response);
			e.printStackTrace();
			DBUtils.rollback(conn);
		} finally {
			DBUtils.setAutoCommit(conn, true);
			SourceUtils.close(pstmt1,pstmt2,conn);
		}
		
	}

}
