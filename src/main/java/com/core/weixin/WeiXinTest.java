package com.core.weixin;

import java.util.Timer;

import net.sf.json.JSONObject;

public class WeiXinTest {
	String [] userList = {"oi1ght-ntzae0WXIiYhLLBcHalq0","oi1ght-E7fjJy-DCBOgg3MHjPkiI","oi1ghtwu4Vq7gYOfADjOC__RxkPQ","oi1ght45-KEcgeqnr5dPyMNc23Fs"};
	public static void main(String[] args) {
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		AccessToken accessToken = weiXinUtil.getAccessToken();
		String menu = JSONObject.fromObject(weiXinUtil.initMenu()).toString();
		//create menu
		int result = weiXinUtil.createMenu(accessToken.getAccess_token(), menu);
		System.out.println(accessToken.getAccess_token());
		Timer timer = new Timer(); 
	    timer.schedule(new SendTask(), 10 * 1000, 60 * 1000);
		if(result == 0){
			System.out.println("create successful");
		}else{
			System.out.println(result);
		}
	}
}
