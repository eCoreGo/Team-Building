package com.core.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import net.sf.json.JSONObject;

public class SendTask extends TimerTask {
	
	private static final String CREATE_ACTIVITY = "create_activity";
	private static final String ACTIVITY_INFO = "activity_info";
	private static final String INFORMATION = "information";
	private static final String ACTIVITY = "activity";
	
	WeiXinUtil weiXinUtil = new WeiXinUtil();
	private List<String> sendMessageStrs = new ArrayList<String>();
	
	public List<String> getSendMessageStrs() {
		return sendMessageStrs;
	}

	public void setSendMessageStrs(List<String> sendMessageStrs) {
		this.sendMessageStrs = sendMessageStrs;
	}

	public void getSendMessage(String type, Data data, List<String> tousers){
		
		for(String touser : tousers){
			SendMessage sendMessage = null;
			String sendMessageStr = "";
			if(null == type || "".equals(type)){
				return;
			}
			if(CREATE_ACTIVITY.equals(type)){
				sendMessage = weiXinUtil.createActivityMessage(data, touser);
			}
			if(ACTIVITY_INFO.equals(type)){
				sendMessage = weiXinUtil.activityInfoMessage(data, touser);
			}
			if(INFORMATION.equals(type)){
				sendMessage = weiXinUtil.infomationMessage(data, touser);
			}
			if(ACTIVITY.equals(type)){
				sendMessage = weiXinUtil.activityMessage(data, touser);
			}
			if(null != sendMessage){
				sendMessageStr = JSONObject.fromObject(sendMessage).toString();
			}
			sendMessageStrs.add(sendMessageStr);
		}
	}
	
	@Override
	public void run() {
		AccessToken accessToken = weiXinUtil.getAccessToken();
		for(String sendMessageStr : sendMessageStrs){
			int sendResult = weiXinUtil.createSendMessage(accessToken.getAccess_token(), sendMessageStr);
			System.out.println(sendMessageStr);
			if(sendResult == 0){
				System.out.println("send successful");
			}else{
				System.out.println(sendResult);
			}
		}
	}

}
