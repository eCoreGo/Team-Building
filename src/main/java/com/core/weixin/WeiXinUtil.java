package com.core.weixin;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class WeiXinUtil {
	
	//private static final String APPID = "wx10f9871ddf10c141";
	//private static final String APPSECRET = "3dbea42661e67dbaae40c0075bf65c5f";
	private static final String APPID = "wx1ab7ea57b3a51ad3";
	private static final String APPSECRET = "4aba6949ab44f431f75c9b5790a13dd9";
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String CREATEMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String SEND_UTL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	private static final String CREATE_ACTIVITY_TEMPLEID = "nz7ICb4U5D5U-w8MomZVVytyKYBVra2jdLvihh3jQ-s";
	private static final String ACTIVITY_INFO_TEMPLEID = "qG7cu0Nru0gHj65W3pFulBSkgPoZIr9IKRvgo08mjA4";
	private static final String INFORMATION_TEMPLEID = "N6q13IYGwXjLiBUUODPUxjXi-kg3XmTV2kJbFYVLPcU";
	private static final String ACTIVITY_TEMPLEID = "zgJ5a0xivrZBJYdQvc7h2gQpI59fAKhaRHEjzKth9tA";
	
	public JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public Menu initMenu(){
		Menu menu = new Menu();
		ClickButton activtes = new ClickButton();
		activtes.setName("活动");
		activtes.setType("click");
		activtes.setKey("activtes");
		
		ClickButton group = new ClickButton();
		group.setName("群组");
		group.setType("click");
		group.setKey("group");
		
		ClickButton aboutMe = new ClickButton();
		aboutMe.setName("我的");
		aboutMe.setType("click");
		aboutMe.setKey("aboutMe");
		
		menu.setButton(new Button[]{activtes,group,aboutMe});
		
		return menu;
	}
	
	public SendMessage createActivityMessage(Data data, String touser){
		SendMessage sendMessage = new SendMessage();
		sendMessage.setTouser(touser);
		sendMessage.setTemplate_id(this.CREATE_ACTIVITY_TEMPLEID);
		sendMessage.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/home.html");
		sendMessage.setData(data);
		return sendMessage;
	}
	
	public SendMessage activityInfoMessage(Data data, String touser){
		SendMessage sendMessage = new SendMessage();
		sendMessage.setTouser(touser);
		sendMessage.setTemplate_id(this.ACTIVITY_INFO_TEMPLEID);
		sendMessage.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/home.html");
		sendMessage.setData(data);
		return sendMessage;
	}
	
	public SendMessage infomationMessage(Data data, String touser){
		SendMessage sendMessage = new SendMessage();
		sendMessage.setTouser(touser);
		sendMessage.setTemplate_id(this.INFORMATION_TEMPLEID);
		sendMessage.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/home.html");
		sendMessage.setData(data);
		return sendMessage;
	}
	
	public SendMessage activityMessage(Data data, String touser){
		SendMessage sendMessage = new SendMessage();
		sendMessage.setTouser(touser);
		sendMessage.setTemplate_id(this.ACTIVITY_TEMPLEID);
		sendMessage.setUrl("http://teambuilding.campusclub.cn:8080/team-building-assistant/home.html");
		sendMessage.setData(data);
		return sendMessage;
	}
	
	public AccessToken getAccessToken(){
		AccessToken token = new AccessToken();
		String url = ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null){
			token.setAccess_token(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}
	
	public int createMenu(String token, String menu){
		int result = 0;
		String url = CREATEMENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
	public int createSendMessage(String token, String sendMessage){
		int result = 0;
		String url = SEND_UTL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, sendMessage);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
}
