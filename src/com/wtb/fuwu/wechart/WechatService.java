package com.wtb.fuwu.wechart;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WechatService {
	public String processRequest(HttpServletRequest request) {
		Map<String, String> map = WechatUtils.xmlToMap(request);
		String fromUserName = (String) map.get("FromUserName");
		String toUserName = (String) map.get("ToUserName");
		String msgType = (String) map.get("MsgType");

		String responseMessage = "success";
		if ("text".equals(msgType)) {
			String content = (String) map.get("Content");
			if (content == null || "".equals(content)) { return responseMessage; }
			try {
				content = URLEncoder.encode(content, "UTF-8");
			} catch (Exception e) {
				System.err.println("消息编码失败：" + content);
				return responseMessage;
			}
			String TTSResult = "测试";
			//			if (TTSResult == null) { return responseMessage; }
			try {
				TextMessage textMessage = new TextMessage();
				textMessage.setMsgType("text");
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(System.currentTimeMillis());
				textMessage.setContent(TTSResult);
				responseMessage = WechatUtils.textMessageToXml(textMessage);
			} catch (Exception e) {
				System.err.println("消息封装失败");
			}
		} else if ("voice".equals(msgType)) {
			System.out.println("接收了一条语音消息");
		}
		System.out.println(responseMessage);
		return responseMessage;
	}
}
