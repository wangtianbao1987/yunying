package com.wtb.fuwu.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wtb.fuwu.data.InitData;
import com.wtb.fuwu.data.MyUtils;
import com.wtb.fuwu.enty.CaidanData;
import com.wtb.fuwu.enty.CaidanDatas;
import com.wtb.fuwu.enty.Price;

@WebServlet("/dingdanConfirm")
public class DingdanConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat df = new DecimalFormat("0.##");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String winId = request.getParameter("winId");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] idss = request.getParameterValues("ids[]");
		if(idss==null || idss.length==0){
			MyUtils.errOut("未点餐", response);
			return;
		}
		String[] prices = request.getParameterValues("price[]");
		String[] counts = request.getParameterValues("count[]");
		double resMoney = 0D;
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		for(int i=0;i<idss.length;i++){
			Map<String,Object> map1 = new HashMap<String,Object>();
			String[] idStrs = idss[i].split("_");
			CaidanDatas cd1 = InitData.getList().get(Integer.parseInt(idStrs[1]));
			CaidanData cd = cd1.getValues().get(Integer.parseInt(idStrs[2]));
			Price p = cd.getValues().get(Integer.parseInt(idStrs[3]));
			map1.put("idx", cd.getIdx());
			map1.put("name", cd.getName());
			if(p.getPrice() != null){
				Integer count = Integer.parseInt(counts[i]);
				if(count < 0){
					MyUtils.errOut("订购数量不能为负数", response);
					return;
				}
				resMoney += p.getPrice()*count;
				map1.put("price", p.getPrice());
				map1.put("count", count);
			}else if(p.getMinPrice() != null && p.getMaxPrice()!=null){
				Integer count = Integer.parseInt(counts[i]);
				if(count < 0){
					MyUtils.errOut("订购数量不能为负数", response);
					return;
				}
				Double price = Double.parseDouble(prices[i]);
				if(price.doubleValue()<p.getMinPrice().doubleValue() || price.doubleValue()>p.getMaxPrice().doubleValue()){
					MyUtils.errOut("订购金额不在订购范围", response);
					return;
				}
				resMoney += price*count;
				map1.put("price", price);
				map1.put("count", count);
			}else if(p.getPerPrice() != null){
				double count = Double.parseDouble(counts[i]);
				if(count < 0){
					MyUtils.errOut("订购数量不能为负数", response);
					return;
				}
				resMoney += p.getPerPrice()*count;
				map1.put("price", p.getPerPrice());
				map1.put("count", count);
			}
			
			int idx = cd.getIdx();
			if(items.size() == 0) {
				items.add(map1);
			}else{
				int start = 0;
				int end = items.size()-1;
				while(true) {
					int yIdx1 = (Integer)items.get(start).get("idx");
					int yIdx2 = (Integer)items.get(end).get("idx");
					int yIdx3 = (Integer)items.get((start+end)/2).get("idx");
					if(yIdx1 >= idx) {
						items.add(start, map1);
						break;
					}else if(yIdx2 <= idx) {
						items.add(end+1,map1);
						break;
					}else if(yIdx3 > idx){
						end = (start+end)/2-1;
					}else if(yIdx3 < idx) {
						start = (start+end)/2+1;
					}
				}
			}
		}
		if(resMoney == 0){
			MyUtils.errOut("未点餐", response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute(winId, items);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resMoney", df.format(resMoney));
		map.put("items", items);
		MyUtils.succOut(map, response);
	}

}
