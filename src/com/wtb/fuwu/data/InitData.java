package com.wtb.fuwu.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.wtb.fuwu.enty.CaidanData;
import com.wtb.fuwu.enty.CaidanDatas;
import com.wtb.fuwu.enty.Price;

public class InitData {
	public static String toJson() {
		List<CaidanDatas> list = getList();
		JSONArray jArr = new JSONArray(list);
		return jArr.toString();
	}
	public static List<CaidanDatas> getList(){
		List<CaidanDatas> list = new ArrayList<CaidanDatas>();
		List<CaidanData> values1 = new ArrayList<CaidanData>();
		List<Price> values2 = new ArrayList<Price>();
		Price p1 = new Price(12.0,"(肉少)");
		values2.add(p1);
		Price p2 = new Price(15.0,"(加肉)");
		values2.add(p2);
		Price p3 = new Price(20.0,"(加肉)");
		values2.add(p3);
		CaidanData c1 = new CaidanData(1, "牛肉粉丝汤", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"(肉少)");
		values2.add(p1);
		p2 = new Price(15.0,"(加肉)");
		values2.add(p2);
		p3 = new Price(20.0,"(加肉)");
		values2.add(p3);
		c1 = new CaidanData(2, "牛杂粉丝汤", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"(肉少)");
		values2.add(p1);
		p2 = new Price(15.0,"(加肉)");
		values2.add(p2);
		p3 = new Price(20.0,"(加肉)");
		values2.add(p3);
		c1 = new CaidanData(3, "牛肉汤面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"(肉少)");
		values2.add(p1);
		p2 = new Price(15.0,"(加肉)");
		values2.add(p2);
		p3 = new Price(20.0,"(加肉)");
		values2.add(p3);
		c1 = new CaidanData(4, "牛杂汤面", values2);
		values1.add(c1);
		
		CaidanDatas cd1 = new CaidanDatas(null, values1);
		list.add(cd1);
		
		
		values1 = new ArrayList<CaidanData>();
		values2 = new ArrayList<Price>();
		p1 = new Price(22.0,"");
		values2.add(p1);
		c1 = new CaidanData(5, "牛肉粉丝汤+牛大骨头送香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(22.0,"");
		values2.add(p1);
		c1 = new CaidanData(6, "牛杂粉丝汤+牛大骨头送香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(22.0,"");
		values2.add(p1);
		c1 = new CaidanData(7, "牛肉汤面+牛大骨头送香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(22.0,"");
		values2.add(p1);
		c1 = new CaidanData(8, "牛杂汤面+牛大骨头送香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(9, "牛肉炒饭+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(10, "牛杂炒饭+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(11, "牛肉炒面+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(12, "牛杂炒面+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(13, "牛肉炒河粉+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(14, "牛杂炒河粉+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0,"");
		values2.add(p1);
		c1 = new CaidanData(15, "牛肉炒米粉+牛肉清汤+炸鸡排送香干/香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(16, "鸡腿饭(2素菜+豆干+香肠+米饭)", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(17, "鸭腿饭(2素菜+豆干+香肠+米饭)'", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(18, "大肉饭(2素菜+豆干+香肠+米饭)", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(19, "大排饭(2素菜+豆干+香肠+米饭)", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(20, "炸鸡排饭(2素菜+豆干+香肠+米饭)", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(21, "鸭头饭(2素菜+豆干+香肠+米饭)", values2);
		values1.add(c1);
		
		cd1 = new CaidanDatas("老板推荐", values1);
		list.add(cd1);
		
		values1 = new ArrayList<CaidanData>();
		values2 = new ArrayList<Price>();
		p1 = new Price(8.0,"");
		values2.add(p1);
		c1 = new CaidanData(22, "青菜鸡蛋炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(23, "牛肉炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(24, "牛杂炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(25, "腊肉炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(26, "鸡腿炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(14.0,"");
		values2.add(p1);
		c1 = new CaidanData(27, "鸭腿炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(14.0,"");
		values2.add(p1);
		c1 = new CaidanData(28, "红烧肉炒饭", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(8.0,"");
		values2.add(p1);
		c1 = new CaidanData(29, "青菜鸡蛋炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(30, "牛肉炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(31, "牛杂炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(32, "腊肉炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(13.0,"");
		values2.add(p1);
		c1 = new CaidanData(33, "鸡腿炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(14.0,"");
		values2.add(p1);
		c1 = new CaidanData(34, "红烧肉炒面", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(8.0,"");
		values2.add(p1);
		c1 = new CaidanData(35, "青菜鸡蛋炒河粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(36, "牛肉炒河粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(37, "牛杂炒河粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(38, "腊肉炒河粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(14.0,"");
		values2.add(p1);
		c1 = new CaidanData(39, "红烧肉炒河粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(8.0,"");
		values2.add(p1);
		c1 = new CaidanData(40, "青菜鸡蛋炒米粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(41, "牛肉炒米粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(42, "牛杂炒米粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(43, "腊肉炒米粉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(14.0,"");
		values2.add(p1);
		c1 = new CaidanData(44, "红烧肉炒米粉", values2);
		values1.add(c1);
		
		cd1 = new CaidanDatas("精美小吃", values1);
		list.add(cd1);
		
		
		values1 = new ArrayList<CaidanData>();
		values2 = new ArrayList<Price>();
		p1 = new Price(12.0,"");
		values2.add(p1);
		c1 = new CaidanData(45, "牛大骨", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0, 40.0, "");
		values2.add(p1);
		c1 = new CaidanData(46, "拌牛肉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0, 40.0, "");
		values2.add(p1);
		c1 = new CaidanData(47, "拌牛杂", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(20.0, 40.0, "");
		values2.add(p1);
		c1 = new CaidanData(48, "拌千张+牛杂", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(6.0,"");
		values2.add(p1);
		c1 = new CaidanData(49, "鸡腿", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(7.0,"");
		values2.add(p1);
		c1 = new CaidanData(50, "鸭腿", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(5.0,"");
		values2.add(p1);
		c1 = new CaidanData(51, "鸭头", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(5.0,"");
		values2.add(p1);
		c1 = new CaidanData(52, "鸭胗", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(6.0,"");
		values2.add(p1);
		c1 = new CaidanData(53, "炸鸡排", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(7.0,"");
		values2.add(p1);
		c1 = new CaidanData(54, "红烧肉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(7.0,"");
		values2.add(p1);
		c1 = new CaidanData(55, "大排", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(2.0,"");
		values2.add(p1);
		c1 = new CaidanData(56, "卤蛋", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(1.0,"");
		values2.add(p1);
		c1 = new CaidanData(57, "豆干", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price(1.0,"");
		values2.add(p1);
		c1 = new CaidanData(58, "香肠", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price("", "斤", 70.0);
		values2.add(p1);
		c1 = new CaidanData(59, "牛肉", values2);
		values1.add(c1);
		
		values2 = new ArrayList<Price>();
		p1 = new Price("", "斤", 70.0);
		values2.add(p1);
		c1 = new CaidanData(60, "牛杂", values2);
		values1.add(c1);
		
		cd1 = new CaidanDatas("另加", values1);
		list.add(cd1);
		return list;
	}
	
	
	
}
